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
        <h1>Da li zelite da obrisete agenciju?</h1>
        <table>
            <thead>
                <tr>
                    <th><%=StringConst.NAME%></th>
                    <th><%=StringConst.DESCRIPTION%></th>
                    <th><%=StringConst.LOCATION%></th>                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%=agency.getName()%></td>
                    <td><%=agency.getDescription()%></td>
                    <td><%=agency.getLocation()%></td>                    
                </tr>
            </tbody>
        </table>


        <form action="AgencyCrudServlet" method="POST">
            <input type="hidden" name="<%=StringConst.ID%>" value="<%=agency.getId()%>"/>
            <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Remove"/>
            <input type="submit" value="Potvrdi" class="btn btn-danger"/>
        </form>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
