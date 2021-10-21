/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Addition;
import beans.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import utility.StringHelpers;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class ReservationCrud implements IBeanCrud<Reservation> {

    @Inject
    AdditionCrud additionCrud;

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        try {
            if (parameters.containsValue("")) {
                return;
            }

            String query = "INSERT INTO rezervacija (pocetak, kraj, ukupnaCena, proslavaId, korisnikId) VALUES (?,?,?,?,?)";

            PreparedStatement pstm = con.prepareStatement(query);

            pstm.setTimestamp(1, StringHelpers.GetDateTimeFromString(parameters.get("start")));
            pstm.setTimestamp(2, StringHelpers.GetDateTimeFromString(parameters.get("end")));
            pstm.setInt(3, GetTotalPrice(Integer.parseInt(parameters.get("celebrationId")), con));
            pstm.setInt(4, Integer.parseInt(parameters.get("celebrationId")));
            pstm.setInt(5, Integer.parseInt(parameters.get("userId")));

            pstm.executeUpdate();
            pstm.close();
        } catch (ParseException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Cancel(int id, Connection con) throws SQLException{
        String query = "UPDATE rezervacija SET otkazana = ? WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setBoolean(1, true);
        pstm.setInt(2, id);
        
        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation GetById(int id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> GetAll(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int GetTotalPrice(int celebrationId, Connection con) throws SQLException {
        List<Addition> additions = additionCrud.GetExistingAddition(celebrationId, con);
        String totalPriceQuery = "SELECT cena FROM proslava WHERE id = ?";
        int totalPrice = 0;
        PreparedStatement totalPricePstm = con.prepareStatement(totalPriceQuery);

        totalPricePstm.setInt(1, celebrationId);

        ResultSet rs = totalPricePstm.executeQuery();
        if (rs.next()) {
            totalPrice = rs.getInt("cena");
        }

        for (Addition addition : additions) {
            totalPrice += addition.getPrice();
        }

        return totalPrice;
    }

}
