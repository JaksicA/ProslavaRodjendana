/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import utility.PasswordHasher;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
@RequestScoped
public class UserCrud implements IBeanCrud<User> {

    @Override
    public void Add(HashMap<String, String> parameters, Connection con) throws SQLException {
        try {
            if(parameters.containsValue("")) return;
            
            String query = "INSERT INTO korisnik (ime, prezime, email, passwordHash, salt, ovlascenjeId) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(query);
            
            String passwordHash = PasswordHasher.GetPasswordHash(parameters.get(StringConst.PASSWORD));
            String salt = "";
            
            pstm.setString(1, parameters.get(StringConst.USER_NAME));
            pstm.setString(2, parameters.get(StringConst.USER_LAST_NAME));
            pstm.setString(3, parameters.get(StringConst.EMAIL));
            pstm.setString(4, passwordHash);
            pstm.setString(5, salt);
            pstm.setInt(6, StringConst.ROLE_ID);
            
            pstm.executeUpdate();
            pstm.close();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    @Override
    public void Remove(String id, Connection con) throws SQLException {
        String query = "DELETE FROM korisnik WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();
        pstm.close();
    }

    @Override
    public void Update(HashMap<String, String> parameters, Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User GetById(int id, Connection con) throws SQLException {
        User user = new User();
        
        String query = "SELECT * FROM korisnik WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("ime"));
            user.setSurname(rs.getString("prezime"));
            user.setOvlascenjeId(rs.getInt("ovlascenjeId"));
        }
        
        return user;
    }

    @Override
    public List<User> GetAll(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
