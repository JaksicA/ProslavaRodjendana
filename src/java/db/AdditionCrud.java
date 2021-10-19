/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Addition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class AdditionCrud implements IBeanCrud<Addition> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        if (parameters.containsValue("")) {
            return;
        }
        String query = "INSERT INTO dodatak (Naziv, Cena) VALUES (?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get("naziv"));
        pstm.setInt(2, Integer.parseInt(parameters.get("cena")));

        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM dodatak WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();

        pstm.close();
    }

    @Override
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException {
        if (parameters.containsValue("")) {
            return;
        }

        String query = "UPDATE dodatak SET naziv = ?, cena = ? WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get(StringConst.NAME));
        pstm.setString(2, parameters.get(StringConst.PRICE));
        pstm.setInt(3, Integer.parseInt(parameters.get(StringConst.ID)));

        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public Addition GetById(int id, Connection con) throws SQLException {
        Addition addition = new Addition();

        String query = "SELECT * FROM dodatak WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            addition.setId(rs.getInt(StringConst.ID));
            addition.setName(rs.getString(StringConst.NAME));
            addition.setPrice(rs.getInt(StringConst.PRICE));
        }

        return addition;
    }

    @Override
    public List<Addition> GetAll(Connection con) throws SQLException {
        List<Addition> additions = new ArrayList<Addition>();

        String query = "SELECT * FROM dodatak";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Addition addition = new Addition(
                    rs.getInt(StringConst.ID),
                    rs.getString(StringConst.NAME),
                    rs.getInt(StringConst.PRICE)
            );
            
            additions.add(addition);
        }
        
        return additions;
    }

    public List<Addition> GetExistingAddition(int celebrationId, Connection con) throws SQLException{
        
        List<Addition> additions = new ArrayList<>();
        
        String query = "SELECT * FROM dodatak d WHERE d.Id IN (SELECT pd.dodatakId FROM proslavadodatak pd WHERE pd.ProslavaId = ?)";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, celebrationId);
        
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Addition adition = new Addition(rs.getInt(StringConst.ID), rs.getString(StringConst.NAME), rs.getInt(StringConst.PRICE));
            additions.add(adition);
        }
        return additions;
    }
    
    public List<Addition> GetAvailableAddition(int celebrationId, Connection con) throws SQLException{
        List<Addition> additions = new ArrayList<>();
        
        String query = "SELECT * FROM dodatak d WHERE d.Id NOT IN (SELECT pd.dodatakId FROM proslavadodatak pd WHERE pd.ProslavaId = ?)";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, celebrationId);        
        
        ResultSet rs = pstm.executeQuery();        
        while(rs.next()){
            Addition adition = new Addition(rs.getInt(StringConst.ID), rs.getString(StringConst.NAME), rs.getInt(StringConst.PRICE));
            additions.add(adition);
        }
        
        return additions;
    }
}
