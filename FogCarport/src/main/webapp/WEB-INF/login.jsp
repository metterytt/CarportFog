<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Login</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">

                    <%-- Login button for employees --%>
                    <%if (request.getAttribute("empLogin") == null) { %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login">
                        <input type="hidden" name="showEmpLogin">
                        <input type="submit" class="btn btn-primary" value="Login som Medarbejder"/><br>
                    </form>

                    <%} else { %>
                    <%-- Login button for customers --%>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login">
                        <input type="hidden" name="showCustomerLogin">
                        <input type="submit" class="btn btn-primary" value="Login Som kunde"/><br>
                    </form>

                    <%}%>
                    <hr class="my-4">

                    <% if (request.getAttribute("empLogin") != null) {%>
                    <h1 class="display-4">Login som Medarbejder</h1>

                    <%-- Login form for employees --%>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login">
                        <input type="hidden" name="loginEmp">
                        <% String error = (String) request.getAttribute("error");
                            if (error != null) {%>
                        <p> <%=error%>
                            <%}%> </p>
                        <div class="form-group">
                            <label for="username">Brugernavn:</label>
                            <input type="email" class="form-control col-md-3" id="username" name="username" value="test@test.dk">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control col-md-3" id="Password" name="password" value="test">
                        </div>
                        <input type="submit" class="btn btn-primary" value="Login"/><br>
                    </form>

                    <%} else { %>
                    <h1 class="display-4">Login som Kunde</h1>

                    <%-- Login form for customers --%>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login">
                        <input type="hidden" name="loginCustomer">
                        <% String error = (String) request.getAttribute("error");
                            if (error != null) {%>
                        <p> <div class="p-2 bg-danger text-black col-md-7 text-center">${error}</div><br>          </p>
                        <%}%> 
                        <div class="form-group">
                            <label for="username">Brugernavn:</label>
                            <input type="email" class="form-control col-md-3" id="username" name="username" value="lars@lars.dk">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control col-md-3" id="Password" name="password" value="123">
                        </div>
                        <input type="submit" class="btn btn-primary" value="Login"/>
                    </form>

                    <br>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="registercustomer">
                        <input type="submit" class="btn btn-primary" value="Opret profil"/>
                    </form>

                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
