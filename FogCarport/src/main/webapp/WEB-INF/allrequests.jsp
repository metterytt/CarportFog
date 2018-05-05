<%-- 
    Document   : allrequests
    Created on : 05-05-2018, 13:33:37
    Author     : mette
--%>

<%@page import="functionLayer.entity.Order"%>
<%@page import="java.util.ArrayList"%>
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

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">

                    <h2>Her er de åbne forespørgsler:</h2>
                    <% ArrayList<Order> openRequests = (ArrayList<Order>) request.getAttribute("openrequests");
                        if (openRequests.size() == 0) {%>
                    <p> Ingen forespørgsler....</p>
                    <% } else { %>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Kunde</th>
                                <th>Længde</th>
                                <th>Bredde</th>
                                <th>Tagvinkel</th>
                                <th>Skur længde</th>
                                <th>Skur bredde</th>
                                <th>Pris</th>
                                <th>Sælger</th>
                                <th>Bestilt</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                for (Order o : openRequests) {
                            %>
                            <tr>
                                <th> <% out.print(o.getOrderID()); %> </th>
                                <th> <% out.print(o.getCustomer()); %> </th>
                                <th> <% out.print(o.getLength()); %> </th>
                                <th> <% out.print(o.getWidth()); %> </th>
                                <th> <% out.print(o.getAngle()); %> </th>
                                <th> <% out.print(o.getShedLength()); %>  </th>
                                <th> <% out.print(o.getShedWidth()); %>  </th>
                                <th> <% out.print(o.getPrice()); %> </th>
                                <th> <% out.print(o.getEmpID()); %> </th>
                                <th> <% out.print(o.isPlaced()); %> </th>

                                <th>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="setordered">
                                        <input type="hidden" name="orderID" value="<%out.print(o.getOrderID());%>" />
                                        <input type="submit" class="btn btn-primary" value="Sæt til bestilt"/>
                                    </form>
                                </th>

                                <%}%>
                            </tr> 
                        </tbody>
                    </table>   
                    <%}%>
                </div>

                <div class="col-md-4">

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
