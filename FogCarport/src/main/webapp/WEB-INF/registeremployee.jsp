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
        <title>Register Employee!</title>
    </head>
    <body>

        <% Employee emp = new Employee("lars", "larsen", "lol");%>
        <%-- <% Employee emp = (Employee) request.getSession().getAttribute("user"); %> --%>

        <h1>Hej <%= emp.getUsername()%> </h1>

        <h2> Registrer ny user til systemet </h2>
        <form action="FrontController" method="POST">
            <div class="form-group">
                <input class="form-control" type="hidden" name="command" value="registerEmployee">
                <label for="username">Email:</label>
                <input class="form-control" type="email" name="username" placeholder="someone@nowhere.com">
                <br>
                <label for="password">Password:</label>
                <input class="form-control" type="password" name="password" value="sesam">
                <br>
                <input class="btn-primary" type="submit" value="Submit">
                
                 <input type="radio" name="empRole" value="IT">IT-Medarbejder
                 <input type="radio" name="empRole" value="salesman">Sælger
                
            </div>
        </form>



    </body>
</html>
