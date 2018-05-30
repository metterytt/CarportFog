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
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">

                    <% Employee emp = (Employee) request.getSession().getAttribute("employee");%>

                    <h1 class="display-4">Hej <%= emp.getUsername()%> </h1> 

                    <h2 class="display-4"> Registrer ny user til systemet </h2>

                    <p class="text-danger">${error}</p>
                    <p class="text-success">${complete}</p> 
                    <div class="form-group">
                        <%-- form to register new employee --%>
                        <form action="FrontController" method="POST">
                            <input class="form-control" type="hidden" name="command" value="registeremployee">
                            <input class="form-control" type="hidden" name="registeremp">
                            <label for="username">Email:</label>
                            <input class="form-control" type="email" name="username" placeholder="Email">
                            <br>
                            <label for="password1">Password:</label>
                            <input class="form-control" type="password" name="password1" placeholder="Password">
                            <br>
                            <label for="password2">Password:</label>
                            <input class="form-control" type="password" name="password2" placeholder="Password">
                            <br>
                            <input type="radio" name="empRole" value="IT">IT
                            <input type="radio" name="empRole" value="salesman">Sælger
                            <br><br>
                            <input class="btn btn-primary" type="submit" value="Opret profil">
                        </form>
                    </div>
                </div>
                <div class="col-md-6">
                    <br>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="navbar">
                        <input type="hidden" name="employee" value="employee">
                        <input type="submit" class="btn btn-primary" value="Tilbage til medarbejderside">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
