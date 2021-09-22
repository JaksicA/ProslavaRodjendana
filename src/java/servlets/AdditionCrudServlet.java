/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Addition;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.ServletRequestHelper;
import utility.StringConst;

/**
 *
 * @author Lenovo
 */
public class AdditionCrudServlet extends HttpServlet {

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
                Addition addition = GetAdditionById(id, con);
                request.setAttribute("addition", addition);

                switch (action) {
                    case "Update":
                        path = "edit.jsp";
                        break;
                    case "Remove":
                        path = "remove.jsp";
                        break;
                    default:
                        path = "additionDetails.jsp";
                        break;
                }
            }
            request.getRequestDispatcher(StringConst.ADDITION_BASE_PATH + path).forward(request, response);
            
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
                    parameters = ServletRequestHelper.getParameters(request, StringConst.NAME, StringConst.PRICE);
                    AddAddition(new ArrayList<>(parameters.values()), con);
                    request.setAttribute("msg", "Uspesno ste kreirali dodatak");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID);
                    RemoveAddition(parameters.get(StringConst.ID), con);
                    request.setAttribute("msg", "Uspesno ste obrisali dodatak");
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID, StringConst.NAME, StringConst.PRICE);
                    UpdateAddition(parameters, con);
                    request.setAttribute("msg", "Uspesno ste izmenili dodatak");
                    break;
                default:
                    break;
            }

            con.close();
            request.getRequestDispatcher("AdditionServlet").forward(request, response);

        } catch (SQLException sqlException) {
            request.setAttribute("poruka", sqlException);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        } catch (ClassNotFoundException classNotFoundException) {
            request.setAttribute("poruka", "Greska prilikom ucitavanja drajvera");
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }

    }

    private void AddAddition(List<String> parameters, Connection con) throws SQLException {
        for (String parameter : parameters) {
            if (parameter.equals("") || parameter.length() == 0) {
                return;
            }
        }
        String query = "INSERT INTO dodatak (Naziv, Cena) VALUES (?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

        for (int i = 0; i < parameters.size(); i++) {
            pstm.setString(i + 1, parameters.get(i));
        }

        pstm.executeUpdate();
        pstm.close();
    }

    private void RemoveAddition(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM dodatak WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();

        pstm.close();
    }

    private void UpdateAddition(HashMap<String, String> parameters, Connection con) throws SQLException {

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

    private Addition GetAdditionById(int id, Connection con) throws SQLException {
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

}
