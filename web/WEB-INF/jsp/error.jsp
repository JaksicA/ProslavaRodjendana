<%-- 
    Document   : error
    Created on : Sep 23, 2021, 11:06:49 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                
         <%String msg = (String) request.getAttribute("poruka");
            if (msg != null) {
        %>
        <h1><%=msg%></h1>
        <%
            }
        %>
    </body>
</html>
