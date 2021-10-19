/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Addition;
import db.AdditionCrud;
import db.CelebrationAdditionCrud;

import db.ConnectionHelpers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.ServletRequestHelper;
import utility.StringConst;

public class CelebrationAdditionCrudServlet extends HttpServlet {

    @Inject
    AdditionCrud additionCrud;
    @Inject
    CelebrationAdditionCrud celebrationAdditionCrud;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int celebrationId = request.getParameter("celebrationId") != null
                    ? Integer.parseInt(request.getParameter("celebrationId"))
                    : Integer.parseInt(request.getAttribute("celebrationId").toString());
            Connection con = ConnectionHelpers.GetConnection();
            List<Addition> existingAdditions = additionCrud.GetExistingAddition(celebrationId, con);
            List<Addition> availableAdditions = additionCrud.GetAvailableAddition(celebrationId, con);

            request.setAttribute("celebrationId", celebrationId);
            request.setAttribute("existingAdditions", existingAdditions);
            request.setAttribute("availableAdditions", availableAdditions);
            request.getRequestDispatcher(StringConst.CELEBRATION_ADDITION_INDEX).forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("poruka", ex.getMessage());
            request.getRequestDispatcher(StringConst.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter(StringConst.ACTION_PARAMETER);
            HashMap<String, String> parameters = ServletRequestHelper.getParameters(request, "celebrationId", "additionId");
            Connection con = ConnectionHelpers.GetConnection();

            switch (action) {
                case "add":
                    celebrationAdditionCrud.Add(parameters, con);
                    break;
                case "remove":
                    celebrationAdditionCrud.Remove(parameters, con);
                    break;
                default:
                    break;
            }

            String path = "CelebrationAdditionCrudServlet?celebrationId=".concat(parameters.get("celebrationId"));
            response.sendRedirect(path);
        } catch (SQLException | ClassNotFoundException ex) {

        }
    }
}
