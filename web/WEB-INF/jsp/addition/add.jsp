<%-- 
    Document   : add
    Created on : Sep 22, 2021, 2:25:12 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.Addition"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <form action="AdditionCrudServlet" method="POST">
            <input name="<%=StringConst.NAME%>" id="<%=StringConst.NAME%>"/>
            <input name="<%=StringConst.PRICE%>" id="<%=StringConst.PRICE%>" />
            
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
