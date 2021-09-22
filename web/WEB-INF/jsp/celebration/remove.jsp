<%-- 
    Document   : remove
    Created on : Sep 20, 2021, 9:59:23 PM
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
        <h1>Da li zelite da obrisete agenciju?</h1>
        <table>
            <thead>
                <tr>
                    <th>Naziv</th>
                    <th>Opis</th>
                    <th>Cena</th>                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=celebration.getName()%></td>
                    <td><%=celebration.getDescription()%></td>
                    <td><%=celebration.getPrice()%></td>                    
                </tr>
            </tbody>
        </table>


        <form action="CelebrationCrudServlet" method="POST">
            <input type="hidden" name="<%=StringConst.ID%>" value="<%=celebration.getId()%>"/>
            <input type="hidden" name="<%="AgencijaId"%>" value="<%=celebration.getAgencyId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Remove"/>
            <input type="submit" value="Potvrdi" class="btn btn-danger"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>

