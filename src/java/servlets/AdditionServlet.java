/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Addition;
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
public class AdditionServlet extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try { 
            Connection con = ConnectionHelpers.GetConnection();
            List<Addition> additions = GetAdditions(con);

            request.setAttribute("additions", additions);
            request.getRequestDispatcher(StringConst.ADDITION_INDEX_PATH).forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("msg", ex);
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

    private List<Addition> GetAdditions(Connection con) throws SQLException {
        List<Addition> additions = new ArrayList<>();

        String query = "SELECT * FROM dodatak";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Addition addition = new Addition(rs.getInt(StringConst.ID), rs.getString(StringConst.NAME), rs.getInt(StringConst.PRICE));
            additions.add(addition);
        }
        return additions;
    }

}
