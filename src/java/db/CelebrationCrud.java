/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Celebration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class CelebrationCrud implements IBeanCrud<Celebration> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        if (parameters.containsValue("")) {
            return;
        }

        String query = "INSERT INTO proslava (Naziv, Opis, Cena, AgencijaId) VALUES (?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get("naziv"));
        pstm.setString(2, parameters.get("opis"));
        pstm.setInt(3, Integer.parseInt(parameters.get("cena")));
        pstm.setInt(4, Integer.parseInt(parameters.get("agencyId")));

        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public Celebration GetById(int id, Connection con) throws SQLException {
        Celebration celebration = new Celebration();

        String query = "SELECT * FROM proslava WHERE Id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            celebration.setId(rs.getInt("id"));
            celebration.setName(rs.getString("naziv"));
            celebration.setDescription(rs.getString("opis"));
            celebration.setPrice(rs.getInt("cena"));
            celebration.setAgencyId(rs.getInt("agencijaId"));
        }
        return celebration;
    }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM proslava WHERE id = ?";
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

        String query = "UPDATE proslava SET Naziv = ?, Opis = ?, Cena = ? WHERE id = ? AND AgencijaId = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setString(1, parameters.get("naziv"));
        pstm.setString(2, parameters.get("opis"));
        pstm.setString(3, parameters.get("cena"));
        pstm.setInt(4, Integer.parseInt(parameters.get("id")));
        pstm.setInt(5, Integer.parseInt(parameters.get("agencijaId")));

        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public List<Celebration> GetAll(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
