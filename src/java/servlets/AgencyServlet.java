/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Agency;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class AgencyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try { 
            Connection con = ConnectionHelpers.GetConnection();
            List<Agency> agencies = GetAgencies(con);

            request.setAttribute("agencies", agencies);
            request.getRequestDispatcher(StringConst.AGENCY_INDEX_PATH).forward(request, response);
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

    private List<Agency> GetAgencies(Connection con) throws SQLException {
        List<Agency> agencies = new ArrayList<>();

        String query = "SELECT * FROM agencija";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Agency agency = new Agency(rs.getInt(StringConst.ID), rs.getString(StringConst.NAME), rs.getString(StringConst.IMAGE), rs.getString(StringConst.DESCRIPTION), rs.getString(StringConst.LOCATION));
            agencies.add(agency);
        }
        return agencies;
    }

}
