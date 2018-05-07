<%-- 
    Document   : RegisterEmployee
    Created on : 04-05-2018, 13:08:37
    Author     : Jesper
--%>

<%@page import="functionLayer.entity.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Register Employee!</title>
    </head>
    <body>
        <%@include file="../Include/Navbar.jspf" %>

        
        <% Employee emp = (Employee) request.getSession().getAttribute("employee"); %>

        <h1>Hej <%= emp.getUsername()%> </h1> 

        <h2> Registrer ny user til systemet </h2>
        
        ${error}
        ${complete}
        <form action="FrontController" method="POST">
            <div class="form-group">
                <input class="form-control" type="hidden" name="command" value="registeremployee">
                <input class="form-control" type="hidden" name="registeremp">
                <label for="username">Email:</label>
                <input class="form-control" type="email" name="username" placeholder="Email:">
                <br>
                <label for="password1">Password:</label>
                <input class="form-control" type="password" name="password1" placeholder="Password:">
                <br>
                <label for="password2">Password:</label>
                <input class="form-control" type="password" name="password2" placeholder="Password:">
                <br>
                <input class="btn btn-primary" type="submit" value="Submit">
                <br>
                 <input type="radio" name="empRole" value="IT">IT
                 <input type="radio" name="empRole" value="salesman">Sælger
                
            </div>
        </form>


        
    </body>
</html>
