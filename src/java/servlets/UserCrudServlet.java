/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import db.ConnectionHelpers;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.PasswordHasher;
import utility.ServletRequestHelper;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class UserCrudServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path;
            String action = request.getParameter(StringConst.ACTION_PARAMETER);

            if (action.equals("Add")) {
                path = "add.jsp";

            } else {
                int id = Integer.parseInt(request.getParameter(StringConst.ID));

                Connection con = ConnectionHelpers.GetConnection();
                User user = GetUserById(id, con);
                request.setAttribute("user", user);

                switch (action) {
                    case "Update":
                        path = "edit.jsp";
                        break;
                    case "Remove":
                        path = "remove.jsp";
                        break;
                    default:
                        path = "userDetails.jsp";
                        break;
                }
            }

            request.getRequestDispatcher(StringConst.USER_BASE_PATH + path).forward(request, response);
        } catch (SQLException sQLException) {
            request.setAttribute("poruka", sQLException);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        } catch (ClassNotFoundException classNotFoundException) {
            request.setAttribute("poruka", classNotFoundException.getMessage());
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(StringConst.ACTION_PARAMETER);
        HashMap<String, String> parameters;

        try {
            Connection con = ConnectionHelpers.GetConnection();
            switch (action) {
                case "Add":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.USER_NAME, StringConst.USER_LAST_NAME, StringConst.EMAIL, StringConst.PASSWORD);
                    AddUser(parameters, con);
                    request.setAttribute("msg", "Uspesno ste kreirali korisnika");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID);
                    RemoveUser(parameters.get(StringConst.ID), con);
                    request.setAttribute("msg", "Uspesno ste obrisali korisnika");
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, "id", "name", "surname", "email", "paswordHash", "salt", "ovlascenjeId");
                    UpdateUser(parameters, con);
                    request.setAttribute("msg", "Uspesno ste izmenili korisnika");
                    break;
                default:
                    break;
            }

            con.close();
            request.getRequestDispatcher("UserServlet").forward(request, response);

        } catch (SQLException sqlException) {
            request.setAttribute("poruka", sqlException);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        } catch (ClassNotFoundException classNotFoundException) {
            request.setAttribute("poruka", "Greska prilikom ucitavanja drajvera");
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("poruka", ex);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }

    }

    private User GetUserById(int id, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void AddUser(HashMap<String, String> parameters, Connection con) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
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
    }

    private void RemoveUser(String get, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void UpdateUser(HashMap<String, String> parameters, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
