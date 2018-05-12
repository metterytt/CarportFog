<%-- 
    Document   : allrequests
    Created on : 05-05-2018, 13:33:37
    Author     : mette
--%>

<%@page import="java.util.List"%>
<%@page import="functionLayer.RenderTables"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="functionLayer.entity.LineItem"%>
<%@page import="functionLayer.BOM"%>
<%@page import="functionLayer.entity.Order"%>
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

            <% if (request.getAttribute("openrequests") == null) { %>
            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="allrequests">
                <input type="hidden" name="showrequests"/>
                <input type="submit" class="btn btn-primary" value="Vis Requests"/>
            </form>
            <% }
                if (request.getAttribute("openrequests") != null) { %>
            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="allrequests">
                <input type="hidden" name="showorders"/>
                <input type="submit" class="btn btn-primary" value="Vis Ordre"/>
            </form>
            <%}%>

            <div class="row">
                <div class="col-md-12">

                    <% List<Order> openRequests = (List<Order>) request.getAttribute("openrequests");%>

                    <h2>Her er de åbne forespørgsler:</h2>

                    <%= RenderTables.getOpenRequestsTable(openRequests)%>

                    <% List<Order> orders = (List<Order>) request.getAttribute("orders");%>

                    <h2>Her er de bestilte ordrer:</h2>

                    <%= RenderTables.getOrdersTable(orders)%>

                </div>

                <div class="col-md-4">
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="backtoemp">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Tilbage til medarbejderside">
                    </form>
                </div>

            </div>
            <div class="row">
                <div class="col-md-8">
                    <br>
                    <br>

                    <%  DecimalFormat formatter = new DecimalFormat("###,##0.00");
                        BOM carportBOM = (BOM) request.getAttribute("carportbom");
                        if (carportBOM != null) {
                            List<LineItem> bom = carportBOM.getListOfProducts();%>
                            <h1>Styklisteberegning</h1>
                            <%= RenderTables.getListOfProducts(bom)%>
                            <h3>Den totale pris for carporten er: <%= formatter.format(carportBOM.totalPrice())%></h3>
                    <% }

                        BOM shedBOM = (BOM) request.getAttribute("shedbom");
                        if (shedBOM != null) {
                            List<LineItem> shedBom = shedBOM.getListOfProducts();%>
                            <h2>Herunder er styklisten for skuret:</h2>
                            <%= RenderTables.getListOfProducts(shedBom)%>
                            <h3>Den totale pris for skuret er: <%= formatter.format(shedBOM.totalPrice())%></h3>
                    <% } %>

                    <%--
                %>
                <h2>Herunder er styklisten for skuret:</h2>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Produktnavn</th>
                                <th>Brug</th>
                                <th>Enhed</th>
                                <th>Antal</th>
                                <th>Pris pr. enhed</th>
                                <th>Ialt kr.</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%  ArrayList<LineItem> shedBom = shedBOM.getListOfProducts();
                                for (LineItem p : shedBom) {
                            %>
                            <tr>
                                <th> <% out.print(p.getName()); %> </th>
                                <th> <% out.print(p.getUseInContext()); %> </th>
                                <th> <% out.print(p.getUom()); %> </th>
                                <th> <% out.print(formatter.format((p.getQuantity()))); %> </th>
                                <th> <% out.print(formatter.format(p.getPricePerUnit())); %>  </th>
                                <th> <% out.print(formatter.format((p.getPricePerUnit() * p.getQuantity()))); %>  </th>
                                    <%}%>
                            </tr> 
                        </tbody>
                    </table> 
                    <h3>Den totale pris for skuret er : <%= formatter.format(shedBOM.totalPrice())%></h3>
                    <%}%>
                    --%>
                </div>
            </div>
        </div>
    </body>
</html>
