<%-- 
    Document   : allcalculations
    Created on : 05-05-2018, 11:43:51
--%>

<%@page import="functionLayer.entity.CustomerCalculation"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                    <h2>Her er de tidligere foretagede beregninger:</h2>
                    <% ArrayList<CustomerCalculation> custCalcs = (ArrayList<CustomerCalculation>) request.getAttribute("custcalcs");
                        if (custCalcs.size() == 0) {%>
                    <p> Ingen beregninger....</p>
                    <% } else { %>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Længde</th>
                                <th>Bredde</th>
                                <th>Tagvinkel</th>
                                <th>Skur længde</th>
                                <th>Skur bredde</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                for (CustomerCalculation cc : custCalcs) {
                            %>
                            <tr>
                                <th> <% out.print(cc.getCalcID()); %> </th>
                                <th> <% out.print(cc.getLength()); %> </th>
                                <th> <% out.print(cc.getWidth()); %> </th>
                                <th> <% out.print(cc.getAngle()); %> </th>
                                <th> <% out.print(cc.getShedLength()); %>  </th>
                                <th> <% out.print(cc.getShedWidth()); %>  </th>
                                    <%}%>
                            </tr> 
                        </tbody>
                    </table>   
                    <%}%>
                </div>
                <div class="col-md-6">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="backtoemp">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Tilbage til medarbejderside">
                    </form>
                </div>
            </div>
        </div>  
    </body>
</html>
