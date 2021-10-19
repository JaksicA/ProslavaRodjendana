package servlets;

import beans.Celebration;
import db.CelebrationCrud;
import db.ConnectionHelpers;
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
public class CelebrationCrudServlet extends HttpServlet {

    @Inject
    CelebrationCrud crud;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String path;
            String action = request.getParameter(StringConst.ACTION_PARAMETER);

            if (action.equals("Add")) {
                String agencyId = request.getParameter("agencyId");
                request.setAttribute("agencyId", agencyId);
                path = "add.jsp";
            } else {
                int id = Integer.parseInt(request.getParameter("id"));

                Connection con = ConnectionHelpers.GetConnection();
                Celebration celebration = crud.GetById(id, con);
                request.setAttribute("celebration", celebration);

                switch (action) {
                    case "Update":
                        path = "edit.jsp";
                        break;
                    case "Remove":
                        path = "remove.jsp";
                        break;
                    default:
                        path = "celebrationDetails.jsp";
                        break;
                }
            }

            request.getRequestDispatcher(StringConst.CELEBRATION_BASE_PATH + path).forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter(StringConst.ACTION_PARAMETER);
        HashMap<String, String> parameters;

        try {
            Connection con = ConnectionHelpers.GetConnection();

            switch (action) {
                case "Add":
                    parameters = ServletRequestHelper.getParameters(request, "agencyId", "naziv", "opis", "cena");
                    crud.Add(parameters, con);
                    request.setAttribute("msg", "Uspesno dodavanje proslave");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, "id", "agencijaId");
                    crud.Remove(parameters.get("id"), con);
                    request.setAttribute("msg", "Uspesno ste obrisali proslavu");
                    request.setAttribute("agencyId", parameters.get("agencijaId"));
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, "id", "naziv", "opis", "cena", "agencijaId");
                    crud.Update(parameters, con);
                    request.setAttribute("msg", "Uspesno azurirana proslava");
                    request.setAttribute("agencyId", parameters.get("agencijaId"));
                    break;
                default:
                    break;
            }

            con.close();
            request.getRequestDispatcher("CelebrationServlet").forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }
}
