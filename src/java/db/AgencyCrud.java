/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Agency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.enterprise.context.RequestScoped;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class AgencyCrud implements IBeanCrud<Agency> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        if (parameters.containsValue("")) {
            return;
        }
            
        String query = "INSERT INTO agencija (Naziv, Slika, Opis, Lokacija) VALUES (?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

       pstm.setString(1, parameters.get("naziv"));
       pstm.setString(2, parameters.get("slika"));
       pstm.setString(3, parameters.get("opis"));
       pstm.setString(4, parameters.get("lokacija"));

        pstm.executeUpdate();
        pstm.close();    }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM agencija WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();

        pstm.close();    }

    @Override
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException {

        if (parameters.containsValue("")) {
            return;
        }

        String query = "UPDATE agencija SET naziv = ?, opis = ?, slika = ?, lokacija = ? WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get(StringConst.NAME));
        pstm.setString(2, parameters.get(StringConst.DESCRIPTION));
        pstm.setString(3, parameters.get(StringConst.IMAGE));
        pstm.setString(4, parameters.get(StringConst.LOCATION));
        pstm.setInt(5, Integer.parseInt(parameters.get(StringConst.ID)));

        pstm.executeUpdate();
        pstm.close();    }

    @Override
    public Agency GetById(int id, Connection con) throws SQLException {
        Agency agency = new Agency();

        String query = "SELECT * FROM agencija WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            agency.setId(rs.getInt(StringConst.ID));
            agency.setDescription(rs.getString(StringConst.DESCRIPTION));
            agency.setImage(rs.getString(StringConst.IMAGE));
            agency.setLocation(rs.getString(StringConst.LOCATION));
            agency.setName(rs.getString(StringConst.NAME));
        }

        return agency;    }

    
}
