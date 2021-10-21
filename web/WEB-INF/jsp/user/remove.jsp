<%-- 
    Document   : remove
    Created on : Sep 22, 2021, 2:25:29 PM
    Author     : Lenovo
--%>

<%@page import="beans.User"%>
<%@page import="utility.StringConst"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <%
            User user = (User)request.getAttribute("user");
        %>
        <h1>Da li zelite da obrisete dodatak?</h1>
        <table>
            <thead>
                <tr>
                    <th><%=StringConst.USER_NAME%></th>
                    <th><%=StringConst.USER_LAST_NAME%></th>               
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=user.getName()%></td>
                    <td><%=user.getSurname()%></td>               
                </tr>
            </tbody>
        </table>


        <form action="UserCrudServlet" method="POST">
            <input type="hidden" name="<%=StringConst.ID%>" value="<%=user.getId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Remove"/>
            <input type="submit" value="Potvrdi" class="btn btn-danger"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
