/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

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
import javax.servlet.http.HttpSession;
import utility.StringHelpers;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class ReservationCrud implements IBeanCrud<Reservation> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        try {
            if(parameters.containsValue("")) return;
            
            String totalPriceQuery = "SELECT cena FROM proslava WHERE id = ?";
            int totalPrice = 0;
            PreparedStatement totalPricePstm = con.prepareStatement(totalPriceQuery);
            
            totalPricePstm.setInt(1, Integer.parseInt(parameters.get("celebrationId")));
            
            ResultSet rs = totalPricePstm.executeQuery();
            if(rs.next()){
                totalPrice = rs.getInt("cena");
            }
            
            String query = "INSERT INTO rezervacija (pocetak, kraj, ukupnaCena, proslavaId, korisnikId) VALUES (?,?,?,?,?)";
            
            PreparedStatement pstm = con.prepareStatement(query);
            
            pstm.setTimestamp(1, StringHelpers.GetDateTimeFromString(parameters.get("start")));
            pstm.setTimestamp(2, StringHelpers.GetDateTimeFromString(parameters.get("end")));
            pstm.setInt(3, totalPrice);
            pstm.setInt(4, Integer.parseInt(parameters.get("celebrationId")));
            pstm.setInt(5, Integer.parseInt(parameters.get("userId")));
            
            pstm.executeUpdate();
            pstm.close();
        } catch (ParseException ex) {
            Logger.getLogger(ReservationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
}
