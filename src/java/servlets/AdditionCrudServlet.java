/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Addition;
import db.AdditionCrud;
import db.ConnectionHelpers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.inject.Inject;
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

    @Inject 
      AdditionCrud crud;
    
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
                Addition addition = crud.GetById(id, con);
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
                    crud.Add(parameters, con);
                    request.setAttribute("msg", "Uspesno ste kreirali dodatak");
                    break;
                case "Remove":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID);
                    crud.Remove(parameters.get(StringConst.ID), con);
                    request.setAttribute("msg", "Uspesno ste obrisali dodatak");
                    break;
                case "Update":
                    parameters = ServletRequestHelper.getParameters(request, StringConst.ID, StringConst.NAME, StringConst.PRICE);
                    crud.Update(parameters, con);
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
}
