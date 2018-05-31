<%@page import="functionLayer.RenderTables"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.entity.CustomerCalculation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<CustomerCalculation> custCalcs = (List<CustomerCalculation>) request.getAttribute("custcalcs");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>All calculations</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h3>Her er de tidligere foretagne beregninger:</h3>
                    <br>
                    <%-- List of all calculations --%>
                    <%= RenderTables.getAllCalculations(custCalcs)%>
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
