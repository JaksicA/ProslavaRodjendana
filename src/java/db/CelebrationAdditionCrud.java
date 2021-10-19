/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.CelebrationAddition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class CelebrationAdditionCrud implements IBeanCrud<CelebrationAddition> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        String query = "INSERT INTO proslavadodatak (proslavaId, dodatakId) VALUES (?,?)";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(parameters.get("celebrationId")));
        pstm.setInt(2, Integer.parseInt(parameters.get("additionId")));
        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Remove(HashMap<String, String> parameters, Connection con) throws SQLException {
        String query = "DELETE FROM proslavadodatak WHERE proslavaId = ? AND dodatakId = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(parameters.get("celebrationId")));
        pstm.setInt(2, Integer.parseInt(parameters.get("additionId")));
        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CelebrationAddition GetById(int id, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CelebrationAddition> GetAll(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
