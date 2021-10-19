package servlets;

import beans.Reservation;
import beans.User;
import db.ConnectionHelpers;
import db.ReservationCrud;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.ServletRequestHelper;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class ReservationCrudServlet extends HttpServlet {

    @Inject
      ReservationCrud crud;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String path;
            String action = request.getParameter(StringConst.ACTION_PARAMETER);
            
            if(action.equals("Add")){                
                String celebrationId = request.getParameter("celebrationId");
                request.setAttribute("celebrationId", celebrationId);
                path = "add.jsp";
            }
            else {
                int id = Integer.parseInt(request.getParameter("id"));
                
                Connection con = ConnectionHelpers.GetConnection();
                Reservation reservation = crud.GetById(id,con);
                request.setAttribute("reservation", reservation);
                
                switch (action){
                    case "Update":
                        path = "edit.jsp";
                        break;
                    case "Remove":
                        path = "remove.jsp";
                        break;
                    default:
                        path = "reservationDetails.jsp";
                        break;
                }          
            }
                            
            request.getRequestDispatcher(StringConst.RESERVATION_BASE_PATH + path).forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter(StringConst.ACTION_PARAMETER);
        HashMap<String , String> parameters;
        
        try{
            Connection con = ConnectionHelpers.GetConnection();
            
            switch(action){
                case "Add":
                    User user = (User)request.getSession().getAttribute("user");
                    parameters = ServletRequestHelper.getParameters(request, "start","end","celebrationId");
                    parameters.put("userId", Integer.toString(user.getId()));
                    crud.Add(parameters, con);
                    request.setAttribute("msg", "Uspesno dodavanje proslave");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, "id", "celebraionId","userId");
                    crud.Remove(parameters.get("id"), con);  
                    request.setAttribute("msg", "Uspesno ste obrisali rezervaciju");
                    request.setAttribute("celebrationId", parameters.get("celebrationId"));
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, "id","start","end","celebrationId","userId");
                    crud.Update(parameters,con);
                    request.setAttribute("msg", "Uspesno azurirana rezervacija");
                    break;
                default:
                    break;
            }
            
            con.close();
            request.getRequestDispatcher("ReservationServlet").forward(request, response);
            
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }
}
