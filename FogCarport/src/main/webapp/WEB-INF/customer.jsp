<%@page import="functionLayer.entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Profile page</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <% Customer customer = (Customer) request.getSession().getAttribute("customer");%>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
         
                    <h1 class="display-4">Kontaktinformationer:</h1>
                    <p class="text-success">  ${message} </p>
                    
                    <%if(request.getAttribute("complete") != null){ %>
                    <div class="p-2 bg-success text-black col-md-7 text-center">${complete}</div><br>
                    <%}%>
                    
                    <%-- List of user information when logged in as customer --%>
                    <div class="card" style="width: 18rem;">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><b>Navn:</b> <%=customer.getName()%></li>
                            <li class="list-group-item"><b>Efternavn:</b> <%=customer.getLastname()%></li>
                            <li class="list-group-item"><b>Email:</b> <%=customer.getEmail()%></li>
                            <li class="list-group-item"><b>Mobilnummer:</b> <%=customer.getPhoneNumber()%></li>
                        </ul>
                    </div>
                    <br>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="customerview">
                        <input type="submit" class="btn btn-primary" value="Ordreoversigt">
                    </form>
                    
                    <%-- checks if a drawing has been made, so the user can go back without losing the entered measurements --%> 
                    <%if(request.getAttribute("backtodrawing") != null){ %>
                    <br>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="hidden" name="backToDrawing">
                        <input type="submit" class="btn btn-primary" value="Tilbage til tegning">
                    </form>
                    
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
