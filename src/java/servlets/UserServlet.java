/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
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
public class UserServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try { 
            Connection con = ConnectionHelpers.GetConnection();
            List<User> users = GetUsers(con);

            request.setAttribute("users", users);
            request.getRequestDispatcher(StringConst.USER_INDEX_PATH).forward(request, response);
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

    private List<User> GetUsers(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM korisnik";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("email"), rs.getString("passwordHash"), rs.getString("salt"),rs.getInt("ovlascenjeId"));
            users.add(user);
        }
        return users;
    }
}
