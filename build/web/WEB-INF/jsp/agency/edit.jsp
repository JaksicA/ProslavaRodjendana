<%-- 
    Document   : edit
    Created on : Sep 12, 2021, 7:41:01 PM
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
        <%
            Agency agency = (Agency) request.getAttribute("agency");
        %>
        <form action="AgencyCrudServlet" method="POST">
            <input name="<%=StringConst.NAME%>" id="<%=StringConst.NAME%>" value="<%=agency.getName()%>"/>
            <input name="<%=StringConst.IMAGE%>" id="<%=StringConst.IMAGE%>" value="<%=agency.getImage()%>"/>
            <input name="<%=StringConst.DESCRIPTION%>" id="<%=StringConst.DESCRIPTION%>" value="<%=agency.getDescription()%>"/>
            <input name="<%=StringConst.LOCATION%>" id="<%=StringConst.LOCATION%>" value="<%=agency.getLocation()%>"/>


            <input type="hidden" name="<%=StringConst.ID%>" value="<%=agency.getId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Update"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
