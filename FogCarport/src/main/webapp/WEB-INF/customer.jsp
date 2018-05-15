<%-- 
    Document   : customer
    Created on : 12-05-2018, 20:04:06
    Author     : Jesper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1>Hai customer! </h1>
                    <p class="text-success">  ${message} </p>



                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="submit" class="btn btn-primary" value="Mine forespørgsler">
                    </form>
                    <br><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="submit" class="btn btn-primary" value="Mine ordre">
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
