<%-- 
    Document   : index
    Created on : Oct 19, 2021, 10:19:16 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Addition"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.jsp"/>
    </head>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            List<Addition> existingAdditions = (ArrayList<Addition>) request.getAttribute("existingAdditions");
            List<Addition> availableAdditions = (ArrayList<Addition>) request.getAttribute("availableAdditions");
            int celebrationId = Integer.parseInt(request.getAttribute("celebrationId").toString());
        %>

        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Naziv</th>
                                <th>Cena</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Addition addition : existingAdditions) {
                            %>
                            <tr>
                                <td><%=addition.getName()%></td>
                                <td><%=addition.getPrice()%></td>
                                <td>
                                    <form method="POST" action="CelebrationAdditionCrudServlet">
                                        <input type="hidden" name="additionId" value="<%=addition.getId()%>"/>
                                        <input type="hidden" name="celebrationId" value="<%=celebrationId%>"/>
                                        <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="remove">
                                        <input type="submit" class="btn btn-sm btn-outline-danger" value="Obrisi"/>
                                    </form>
                                </td>
                            </tr>
                            <%
                                }
                            %>

                        </tbody>
                    </table>
                </div>
                <div class="col-md-6">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Naziv</th>
                                <th>Cena</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Addition addition : availableAdditions) {
                            %>
                            <tr>
                                <td><%=addition.getName()%></td>
                                <td><%=addition.getPrice()%></td>
                                <td>
                                    <form method="POST" action="CelebrationAdditionCrudServlet">
                                        <input type="hidden" name="additionId" value="<%=addition.getId()%>"/>
                                        <input type="hidden" name="celebrationId" value="<%=celebrationId%>"/>
                                        <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="add">
                                        <input type="submit" class="btn btn-sm btn-outline-success" value="Dodaj"/>
                                    </form>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
