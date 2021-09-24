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
            String agencyId = (String)request.getAttribute("agencyId");
        %>
        <form action="CelebrationCrudServlet" method="POST">
            <div class="center-screen">
                <div>
                    <label for="naziv">Naziv:</label>
                    <input name="<%="naziv"%>" id="<%="naziv"%>"/>
                </div>
                <div>
                    <label for="opis">Opis proslave:</label>
                    <input name="<%="opis"%>" id="<%="opis"%>"/>  
                </div>
                <div>
                    <label for="cena">Cena:</label>
                    <input name="<%="cena"%>" id="<%="cena"%>"/>
                </div>
                <input type="hidden" name="agencyId" id="agencyId", value="<%=agencyId%>"/>
                <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
                <input type="submit" value="Potvrdi" class="btn btn-primary"/>
            </div>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
