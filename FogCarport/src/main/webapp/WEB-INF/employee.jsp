<%-- 
    Document   : employee
    Created on : 04-05-2018, 11:13:39
    Author     : mette
--%>

<%@page import="functionLayer.entity.LineItem"%>
<%@page import="functionLayer.BOM"%>
<%@page import="functionLayer.entity.CustomerCalculation"%>
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
                <div class="col-md-6">
                    
                    <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="registeremployee">
                            <br/>
                            <input type="submit" class="btn btn-primary" value="Registrer ny medarbejder">
                        </form>
                    
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
            </div>
        </div>



        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <br>
                    <h1>Styklisteberegning</h1>
                    <br>
                    <br>

                    <% BOM carportBOM = (BOM) session.getAttribute("carportbom");
                    %>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Produktnavn</th>
                                <th>Brug</th>
                                <th>Enhed</th>
                                <th>Antal</th>
                                <th>Pris</th>

                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<LineItem> bom = carportBOM.getListOfProducts();
                                for (LineItem p : bom) {
                            %>
                            <tr>
                                <th> <% out.print(p.getName()); %> </th>
                                <th> <% out.print(p.getUseInContext()); %> </th>
                                <th> <% out.print(p.getUom()); %> </th>
                                <th> <% out.print(p.getQuantity()); %> </th>
                                <th> <% out.print(p.getPrice()); %>  </th>

                                <%}%>
                            </tr> 
                        </tbody>
                    </table>    
                    <% if (request.getSession().getAttribute("shedbom") != null) {
                            BOM shedBOM = (BOM) session.getAttribute("shedbom");
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
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<LineItem> shedBom = shedBOM.getListOfProducts();
                                for (LineItem p : shedBom) {
                            %>
                            <tr>
                                <th> <% out.print(p.getName()); %> </th>
                                <th> <% out.print(p.getUseInContext()); %> </th>
                                <th> <% out.print(p.getUom()); %> </th>
                                <th> <% out.print(p.getQuantity()); %> </th>
                                <th> <% out.print(p.getPrice()); %>  </th>
                                    <%}%>
                            </tr> 
                        </tbody>
                    </table>    
                    <%}%>

                </div>









                </body>
                </html>
