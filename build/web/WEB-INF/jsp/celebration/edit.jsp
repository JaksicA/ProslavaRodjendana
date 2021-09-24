<%-- 
    Document   : edit
    Created on : Sep 20, 2021, 9:59:11 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.Celebration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            Celebration celebration = (Celebration) request.getAttribute("celebration");
        %>
        <form action="CelebrationCrudServlet" method="POST">
            <input name="<%=StringConst.NAME%>" id="<%=StringConst.NAME%>" value="<%=celebration.getName()%>"/>
            <input name="<%=StringConst.DESCRIPTION%>" id="<%=StringConst.DESCRIPTION%>" value="<%=celebration.getDescription()%>"/>
            <input name="<%=StringConst.PRICE%>" id="<%=StringConst.PRICE%>" value="<%=celebration.getPrice()%>"/>


            <input type="hidden" name="<%=StringConst.ID%>" value="<%=celebration.getId()%>"/>
            <input type="hidden" name="<%="agencijaId"%>" value="<%=celebration.getAgencyId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Update"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
