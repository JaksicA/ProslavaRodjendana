/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Addition;
import beans.Celebration;
import db.AdditionCrud;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class CelebrationServlet extends HttpServlet {

    @Inject AdditionCrud additionCrud; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = ConnectionHelpers.GetConnection();
            String agencyId = request.getParameter("agencyId") != null
                    ? request.getParameter("agencyId")
                    : (String) request.getAttribute("agencyId");
            List<Celebration> celebrations = GetCelebrations(con, agencyId);
            HashMap<Integer, List<Addition>> celebrationsAdditions = GetCelebrationAddition(celebrations, con);
            
            request.setAttribute("celebrationsAdditions", celebrationsAdditions);
            request.setAttribute("agencyId", agencyId);
            request.setAttribute("celebrations", celebrations);
            request.getRequestDispatcher(StringConst.CELEBRATION_INDEX_PATH).forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private List<Celebration> GetCelebrations(Connection con, String agencyId) throws SQLException {
        List<Celebration> celebrations = new ArrayList<>();

        String query = "SELECT * FROM proslava WHERE agencijaId = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(agencyId));

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Celebration celebration = new Celebration(rs.getInt("id"), rs.getString("naziv"), rs.getString("opis"), rs.getInt("cena"), rs.getInt("agencijaId"));
            celebrations.add(celebration);
        }
        return celebrations;
    }
    
    private HashMap<Integer, List<Addition>> GetCelebrationAddition(List<Celebration> celebrations, Connection con) throws SQLException{
        HashMap<Integer, List<Addition>> celebrationsAdditions = new HashMap<>();
        
        for(Celebration celebration : celebrations){
            List<Addition> additions = additionCrud.GetExistingAddition(celebration.getId(), con);
            celebrationsAdditions.put(celebration.getId(), additions);
        }
        
        return celebrationsAdditions;
    }

}
