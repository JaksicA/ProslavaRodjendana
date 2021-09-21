<%-- 
    Document   : add
    Created on : Sep 12, 2021, 8:38:52 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.Agency"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <form action="AgencyCrudServlet" method="POST">
            <input name="<%=StringConst.NAME%>" id="<%=StringConst.NAME%>"/>
            <input name="<%=StringConst.IMAGE%>" id="<%=StringConst.IMAGE%>" />
            <input name="<%=StringConst.DESCRIPTION%>" id="<%=StringConst.DESCRIPTION%>"/>            
            <input name="<%=StringConst.LOCATION%>" id="<%=StringConst.LOCATION%>"/>

            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>