<%-- 
    Document   : index
    Created on : Sep 12, 2021, 4:21:47 PM
    Author     : Lenovo
--%>

<%@page import="java.util.List"%>
<%@page import="beans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>

    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
        %>

        <a class="btn btn-primary" href="UserCrudServlet?action=Add">Dodaj korisnika</a>
        <div class="container">
            <div class="row">
                <% for (User user : users) {%>
                <div class="col-md-4">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <%= user.getName()%> 
                            <hr class="my-4"/>
                        </div>
                        <a class="btn btn-danger" href="UserCrudServlet?id=<%=user.getId()%>&action=Remove">Obrisi</a>
                        <a class="btn btn-primary" href="UserCrudServlet?id=<%= user.getId()%>&action=Update">Izmeni</a>
                    </div>
                    <input type="hidden" value="<%= user.getId()%>" />
                </div>
                <% }%>
            </div>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
