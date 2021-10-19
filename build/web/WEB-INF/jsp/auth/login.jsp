<%-- 
    Document   : login
    Created on : Oct 19, 2021, 12:38:29 AM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../header.jsp"/>
    </head>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <div class="container">
            <form method="POST" action="AuthServlet">
                <div class="row">
                    <div class="offset-md-2 col-md-8">
                        <div class="form-group">
                            <label for="<%=StringConst.EMAIL%>">E-mail</label>
                            <input class="form-control" id="<%=StringConst.EMAIL%>" name="<%=StringConst.EMAIL%>"/>
                        </div>
                    </div>
                    <div class="offset-md-2 col-md-8">
                        <div class="form-group">
                            <label for="<%=StringConst.PASSWORD%>">Lozinka</label>
                            <input class="form-control" id="<%=StringConst.PASSWORD%>" name="<%=StringConst.PASSWORD%>" type="password"/>
                        </div>
                    </div>
                    <div class="offset-md-2 col-md-8">
                        <input type="submit" class="btn btn-primary"/>                            
                    </div>
                </div>
            </form>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
