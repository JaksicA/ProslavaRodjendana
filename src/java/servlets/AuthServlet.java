/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import db.ConnectionHelpers;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utility.PasswordHasher;
import utility.ServletRequestHelper;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(StringConst.AUTH_LOGIN_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            User user = new User();
            HashMap<String,String> parameters = ServletRequestHelper.getParameters(request, StringConst.EMAIL, StringConst.PASSWORD);
            
            Connection con = ConnectionHelpers.GetConnection();
            
            String query = "SELECT * FROM korisnik WHERE email = ?";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.setString(1, parameters.get(StringConst.EMAIL));
            
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt(StringConst.ID));
                user.setEmail(rs.getString(StringConst.EMAIL));
                user.setName(rs.getString(StringConst.USER_NAME));
                user.setSurname(rs.getString(StringConst.USER_LAST_NAME));
                user.setPasswordHash(rs.getString("PasswordHash"));
                user.setOvlascenjeId(rs.getInt("OvlascenjeId"));
            }else{
                request.setAttribute("poruka", "Email ili password nisu ispravni");
                request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
                return;
            }
            
            rs.close();
            con.close();
            
            boolean isValidPassword = PasswordHasher.validatePassword(parameters.get(StringConst.PASSWORD), user.getPasswordHash());
            if(!isValidPassword){
                request.setAttribute("poruka", "Email ili password nisu ispravni");
                request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
                return;
            }
            
            HttpSession session = request.getSession();
            
            session.setAttribute("user", user);
            request.getRequestDispatcher("AgencyServlet").forward(request, response);
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException | SQLException | ServletException ex) {
            request.setAttribute("poruka", ex.getMessage());
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
        
    }


}
