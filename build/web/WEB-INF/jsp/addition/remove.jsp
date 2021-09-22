<%-- 
    Document   : remove
    Created on : Sep 22, 2021, 2:25:29 PM
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
        <h1>Da li zelite da obrisete dodatak?</h1>
        <table>
            <thead>
                <tr>
                    <th><%=StringConst.NAME%></th>
                    <th><%=StringConst.PRICE%></th>               
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=addition.getName()%></td>
                    <td><%=addition.getPrice()%></td>               
                </tr>
            </tbody>
        </table>


        <form action="AdditionCrudServlet" method="POST">
            <input type="hidden" name="<%=StringConst.ID%>" value="<%=addition.getId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Remove"/>
            <input type="submit" value="Potvrdi" class="btn btn-danger"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
