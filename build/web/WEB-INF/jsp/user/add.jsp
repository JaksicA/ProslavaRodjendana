<%-- 
    Document   : add
    Created on : Sep 22, 2021, 2:25:12 PM
    Author     : Lenovo
--%>

<%@page import="utility.StringConst"%>
<%@page import="beans.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../header.jsp"/>
    <body>
        <jsp:include page="../navbar.jsp"/>
        <div class="container">
            <div class="col-md-12 mx-auto">
                <form action="UserCrudServlet" method="POST">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="<%=StringConst.USER_NAME%>">Ime</label>
                                <input class="form-control" name="<%=StringConst.USER_NAME%>" id="<%=StringConst.USER_NAME%>"/>
                            </div>                    
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="<%=StringConst.USER_LAST_NAME%>">Prezime</label>
                                <input class="form-control" name="<%=StringConst.USER_LAST_NAME%>" id="<%=StringConst.USER_LAST_NAME%>" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="<%=StringConst.EMAIL%>">E-mail</label>
                                <input class="form-control" name="<%=StringConst.EMAIL%>" id="<%=StringConst.EMAIL%>" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">

                            <div class="form-group">
                                <label for="<%=StringConst.PASSWORD%>">Lozinka</label>
                                <input class="form-control" name="<%=StringConst.PASSWORD%>" id="<%=StringConst.PASSWORD%>" type="password"/>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="<%=StringConst.ACTION_PARAMETER%>" value="Add"/>
                    <input type="submit" value="Potvrdi" class="btn btn-primary"/>
                </form>
            </div>
        </div>
        <jsp:include page="../scripts.jsp"/>
    </body>
</html>
