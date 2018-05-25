<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Opret profil</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1 class="display-4">Opret profil</h1>
                    <p class="text-danger">${error}</p>
                    <div class="form-group">
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="command" value="registercustomer">
                            <input type="hidden" name="registercus">
                            <label for="firstname">Fornavn:</label>
                            <input class="form-control col-md-6" type="text" name="firstname">
                            <br>
                            <label for="surname">Efternavn:</label>
                            <input class="form-control col-md-6" type="text" name="lastname">
                            <br>
                            <label for="username">Mobilnummer:</label>
                            <input class="form-control col-md-6" type="number" name="mobilenumber">
                            <br>
                            <label for="username">Brugernavn:</label>
                            <input class="form-control col-md-6" type="email" name="username" placeholder="eksempel@eksempel.dk">
                            <br>
                            <label for="password1">Kodeord:</label>
                            <input class="form-control col-md-6" type="password" name="password1">
                            <br>
                            <label for="password2">Gentag Kodeord:</label>
                            <input class="form-control col-md-6" type="password" name="password2">
                            <br>
                            <input class="btn btn-primary" type="submit" value="Opret profil">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
