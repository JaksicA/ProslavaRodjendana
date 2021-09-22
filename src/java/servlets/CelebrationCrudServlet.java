/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Celebration;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String path;
            String action = request.getParameter(StringConst.ACTION_PARAMETER);
            
            if(action.equals("Add")){
                path = "add.jsp";
            }
            else {
                int id = Integer.parseInt(request.getParameter("id"));
                
                Connection con = ConnectionHelpers.GetConnection();
                Celebration celebration = GetCelebrationById(id,con);
                request.setAttribute("celebration", celebration);
                
                switch (action){
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
            request.setAttribute("msg", ex);
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
                    parameters = ServletRequestHelper.getParameters(request, "naziv","opis","cena");
                    AddCelebration(new ArrayList<>(parameters.values()), con);
                    request.setAttribute("msg", "Uspesno dodavanje proslave");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, "id");
                    RemoveCelebration(parameters.get("Id"), con);  
                    request.setAttribute("msg", "Uspesno ste obrisali proslavu");
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, "id","naziv","opis","cena","agencijaId");
                    UpdateCelebration(parameters,con);
                    request.setAttribute("msg", "Uspesno azurirana proslava");
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

    private Celebration GetCelebrationById(int id, Connection con) throws SQLException {
        Celebration celebration = new Celebration();
        
        String query = "SELECT * FROM proslava WHERE Id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1,id);
        
        ResultSet rs = pstm.executeQuery();
        
        if(rs.next()){
            celebration.setId(rs.getInt("id"));
            celebration.setName(rs.getString("name"));
            celebration.setDescription(rs.getString("description"));
            celebration.setPrice(rs.getInt("price"));
            celebration.setAgencyId(rs.getInt("agencyId"));
        }
        return celebration;
    }
    private void AddCelebration(List<String> parameters , Connection con) throws SQLException{
        for (String parameter : parameters) {
            if (parameter.equals("") || parameter.length() == 0) {
                return;
            }
        }
        String query = "INSERT INTO proslava (Naziv, Opis, Cena) VALUES (?,?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

        for (int i = 0; i < parameters.size(); i++) {
            pstm.setString(i + 1, parameters.get(i));
        }

        pstm.executeUpdate();
        pstm.close();
    }

    private void RemoveCelebration(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM proslava WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();

        pstm.close();
    }

    private void UpdateCelebration(HashMap<String, String> parameters, Connection con) throws SQLException {
        if (parameters.containsValue("")) {
            return;
        }

        String query = "UPDATE proslava SET Naziv = ?, Opis = ?, Cena = ? WHERE id = ? AND AgencijaId = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get("name"));
        pstm.setString(2, parameters.get("description"));
        pstm.setString(3, parameters.get("price"));
        pstm.setInt(4, Integer.parseInt(parameters.get("id")));
        pstm.setInt(5, Integer.parseInt(parameters.get("agencyId")));

        pstm.executeUpdate();
        pstm.close();
    }
}
