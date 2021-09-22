<%-- 
    Document   : edit
    Created on : Sep 22, 2021, 2:25:20 PM
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
        <%
            Addition addition = (Addition) request.getAttribute("addition");
        %>
        <form action="AdditionCrudServlet" method="POST">
            <input name="<%=StringConst.NAME%>" id="<%=StringConst.NAME%>" value="<%=addition.getName()%>"/>
            <input name="<%=StringConst.PRICE%>" id="<%=StringConst.PRICE%>" value="<%=addition.getPrice()%>"/>


            <input type="hidden" name="<%=StringConst.ID%>" value="<%=addition.getId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Update"/>
            <input type="submit" value="Potvrdi" class="btn btn-primary"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>