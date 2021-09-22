<%-- 
    Document   : index
    Created on : Sep 22, 2021, 2:25:05 PM
    Author     : Lenovo
--%>

<%@page import="java.util.List"%>
<%@page import="beans.Addition"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>

    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            List<Addition> additions = (List<Addition>) request.getAttribute("additions");
        %>

        <a class="btn btn-primary" href="AdditionCrudServlet?action=Add">Dodaj dodatak:D</a>
        <div class="container">
            <div class="row">
                <% for (Addition addition : additions) {%>
                <div class="col-md-4">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4"><%= addition.getName()%></h1>
                            <p class="lead"><%= addition.getPrice()%></p>
                        </div>
                        <a class="btn btn-danger" href="AdditionCrudServlet?id=<%=addition.getId()%>&action=Remove">Obrisi</a>
                        <a class="btn btn-primary" href="AdditionCrudServlet?id=<%= addition.getId()%>&action=Update">Izmeni</a>
                    </div>
                    <input type="hidden" value="<%= addition.getId()%>" />
                </div>
                <% }%>
            </div>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
