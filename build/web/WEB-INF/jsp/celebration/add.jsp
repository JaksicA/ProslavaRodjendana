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
        <form action="CelebrationCrudServlet" method="POST">
            <input name="<%="naziv"%>" id="<%="naziv"%>"/>
            <input name="<%="opis"%>" id="<%="opis"%>"/>            
            <input name="<%="cena"%>" id="<%="cena"%>"/>

            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
