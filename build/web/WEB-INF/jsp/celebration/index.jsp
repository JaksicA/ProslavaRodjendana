<%-- 
    Document   : index
    Created on : Sep 20, 2021, 3:38:19 PM
    Author     : Lenovo
--%>

<%@page import="beans.Addition"%>
<%@page import="java.util.HashMap"%>
<%@page import="beans.Celebration"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>

    <body>
        <jsp:include page="../navbar.jsp"/>

        <%
            List<Celebration> celebrations = (List<Celebration>) request.getAttribute("celebrations");
            HashMap<Integer, List<Addition>> celebrationsAdditions = (HashMap<Integer, List<Addition>>) request.getAttribute("celebrationsAdditions");
            String agencyId = (String) request.getAttribute("agencyId");
        %>

        <a class="btn btn-primary" href="CelebrationCrudServlet?agencyId=<%=agencyId%>&action=Add">Dodaj proslavu</a>
        <div class="container">
            <div class="row">
                <% for (Celebration celebration : celebrations) {%>
                <div class="col-md-4">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <hr class="my-4"/>
                            <h1 class="display-4"><%= celebration.getName()%></h1>
                            <p class="lead"><%= celebration.getDescription()%></p>
                            <p class="text-muted"><%= celebration.getPrice()%> </p>
                        </div>
                        <a class="btn btn-danger" href="CelebrationCrudServlet?id=<%=celebration.getId()%>&action=Remove">Obrisi</a>
                        <a class="btn btn-primary" href="CelebrationCrudServlet?id=<%= celebration.getId()%>&action=Update">Izmeni</a>
                        <a class="btn btn-success" href="ReservationCrudServlet?celebrationId=<%= celebration.getId()%>&action=Add">Rezervisi</a>
                        <a class="btn btn-warning" href="CelebrationAdditionCrudServlet?celebrationId=<%= celebration.getId()%>">Dodaci</a>
                        <hr/>
                        <h5>Dodaci</h5>
                        <ul>
                            <%
                                for (Addition addition : celebrationsAdditions.get(celebration.getId())) {
                            %>
                            <li><%=addition.getName()%></li>
                                <%
                                    }
                                %>
                        </ul>
                    </div>
                    <input type="hidden" value="<%= celebration.getId()%>" />.
                    <input type="hidden" value="<%= celebration.getAgencyId()%>" />
                </div>
                <% }%>
            </div>
        </div>


        <jsp:include page="../scripts.jsp"/>
    </body>
</html>