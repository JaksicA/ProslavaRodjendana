<%-- 
    Document   : add
    Created on : Sep 20, 2021, 4:10:35 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            String celebrationId = (String) request.getAttribute("celebrationId");
        %>
        <form action="ReservationCrudServlet" method="POST">
            <div class="center-screen">
                <div>
                    <label for="start">Pocetak:</label>
                    <input type="datetime-local" name="<%="start"%>" id="<%="start"%>" pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}"/>
                </div>
                <div>
                    <label for="end">Kraj:</label>
                    <input type="datetime-local" name="<%="end"%>" id="<%="end"%>"/>  
                </div>
                <input type="hidden" name="celebrationId" id="celebrationId", value="<%=celebrationId%>"/>
                <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
                <input type="submit" value="Potvrdi" class="btn btn-primary"/>
            </div>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
