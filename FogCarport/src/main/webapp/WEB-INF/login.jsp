<%-- 
    Document   : login
    Created on : 04-05-2018, 11:54:57
    Author     : Rasmus
--%>

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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="display-4">Login</h1>
                    <br>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login">
                        <% String error = (String) request.getAttribute("error");
                            if (error != null) {%>
                        <p> <%=error%>
                            <%}%> </p>
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="email" class="form-control" id="username" name="username" value="test@test.dk">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="Password" name="password" value="test">
                        </div>
                        <br>
                        <input type="submit" class="btn btn-primary" value="Login"/><br>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>