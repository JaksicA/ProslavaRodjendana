/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Reservation;
import beans.User;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ReservationServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = ConnectionHelpers.GetConnection();
            
            String celebrationId = request.getParameter("celebrationId") != null
                    ? request.getParameter("celebrationId")
                    : (String) request.getAttribute("celebrationId");
            
            User user = (User)request.getSession().getAttribute("user");
            
            List<Reservation> reservations = GetReservations(con, celebrationId, user.getId());
            
            request.setAttribute("celebrationId", celebrationId);
            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher(StringConst.RESERVATION_INDEX_PATH).forward(request, response);
            
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

    private List<Reservation> GetReservations(Connection con, String celebrationId, int userId) throws SQLException {
                List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT * FROM rezervacija WHERE proslavaId = ? AND korisnikId = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(celebrationId));
        pstm.setInt(2, userId);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Reservation reservation = new Reservation(rs.getInt("id"), rs.getInt("ukupnaCena"), rs.getDate("pocetak"), rs.getDate("kraj"), rs.getBoolean("otkazana"), rs.getInt("proslavaId"), rs.getInt("korisnikId"));
            reservations.add(reservation);
        }
        return reservations;
    }
}
