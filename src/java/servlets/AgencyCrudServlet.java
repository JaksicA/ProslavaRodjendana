/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Agency;
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

public class AgencyCrudServlet extends HttpServlet {

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
                Agency agency = GetAgencyById(id, con);
                request.setAttribute("agency", agency);

                switch (action) {
                    case "Update":
                        path = "edit.jsp";
                        break;
                    case "Remove":
                        path = "remove.jsp";
                        break;
                    default:
                        path = "agencyDetails.jsp";
                        break;
                }
            }

            request.getRequestDispatcher(StringConst.AGENCY_BASE_PATH + path).forward(request, response);
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
                    parameters = ServletRequestHelper.getParameters(request, StringConst.NAME, StringConst.IMAGE, StringConst.DESCRIPTION, StringConst.LOCATION);
                    AddAgency(new ArrayList<>(parameters.values()), con);
                    request.setAttribute("msg", "Uspesno ste kreirali agenciju");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID);
                    RemoveAgency(parameters.get(StringConst.ID), con);
                    request.setAttribute("msg", "Uspesno ste obrisali agenciju");
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID, StringConst.NAME, StringConst.IMAGE, StringConst.DESCRIPTION, StringConst.LOCATION);
                    UpdateAgency(parameters, con);
                    request.setAttribute("msg", "Uspesno ste izmenili agenicju");
                    break;
                default:
                    break;
            }

            con.close();
            request.getRequestDispatcher("AgencyServlet").forward(request, response);

        } catch (SQLException sqlException) {
            request.setAttribute("poruka", sqlException);
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        } catch (ClassNotFoundException classNotFoundException) {
            request.setAttribute("poruka", "Greska prilikom ucitavanja drajvera");
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }

    }

    private void AddAgency(List<String> parameters, Connection con) throws SQLException {
        for (String parameter : parameters) {
            if (parameter.equals("") || parameter.length() == 0) {
                return;
            }
        }
        String query = "INSERT INTO agencija (Naziv, Slika, Opis, Lokacija) VALUES (?,?,?,?)";
        PreparedStatement pstm = con.prepareStatement(query);

        for (int i = 0; i < parameters.size(); i++) {
            pstm.setString(i + 1, parameters.get(i));
        }

        pstm.executeUpdate();
        pstm.close();
    }

    private void RemoveAgency(String id, Connection con) throws SQLException {
        if (id.equals("")) {
            return;
        }

        String query = "DELETE FROM agencija WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, Integer.parseInt(id));
        pstm.executeUpdate();

        pstm.close();
    }

    private void UpdateAgency(HashMap<String, String> parameters, Connection con) throws SQLException {

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
        pstm.close();
    }

    private Agency GetAgencyById(int id, Connection con) throws SQLException {
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

        return agency;
    }

}
