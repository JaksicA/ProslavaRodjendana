<%-- 
    Document   : navbar
    Created on : Sep 11, 2021, 10:20:08 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="AgencyServlet">Agencije</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Rezervacije</a>
            </li>
        </ul>  
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="AuthServlet">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="AuthServlet?logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Register</a>
            </li>
        </ul>
    </div>
</nav>