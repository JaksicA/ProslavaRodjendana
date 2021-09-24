<%-- 
    Document   : add
    Created on : Sep 22, 2021, 2:25:12 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <form action="UserCrudServlet" method="POST">
            <input name="<%=StringConst.USER_NAME%>" id="<%=StringConst.USER_NAME%>"/>
            <input name="<%=StringConst.USER_LAST_NAME%>" id="<%=StringConst.USER_LAST_NAME%>" />
            <input name="<%=StringConst.EMAIL%>" id="<%=StringConst.EMAIL%>" />
            <input name="<%=StringConst.PASSWORD%>" id="<%=StringConst.PASSWORD%>" />
            
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
