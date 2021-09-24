<%-- 
    Document   : index
    Created on : Sep 12, 2021, 4:21:47 PM
    Author     : Lenovo
--%>

<%@page import="java.util.List"%>
<%@page import="beans.Agency"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>

    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            List<Agency> agencies = (List<Agency>) request.getAttribute("agencies");
        %>

        <a class="btn btn-primary" href="AgencyCrudServlet?action=Add">Dodaj agenciju</a>
        

        <div class="container">
            <div class="row">
                <% for (Agency agency : agencies) {%>
                <div class="col-md-4">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <%= agency.getImage()%> 
                            <hr class="my-4"/>
                            <h1 class="display-4"><%= agency.getName()%></h1>
                            <p class="lead"><%= agency.getDescription()%></p>
                            <p class="text-muted"><%= agency.getLocation()%> </p>
                        </div>
                        <a class="btn btn-danger" href="AgencyCrudServlet?id=<%=agency.getId()%>&action=Remove">Obrisi</a>
                        <a class="btn btn-primary" href="AgencyCrudServlet?id=<%= agency.getId()%>&action=Update">Izmeni</a>
                        <a class="btn btn-success" href="CelebrationServlet?agencyId=<%=agency.getId()%>">Proslave</a>
                    </div>
                    <input type="hidden" value="<%= agency.getId()%>" />
                </div>
                <% }%>
            </div>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
