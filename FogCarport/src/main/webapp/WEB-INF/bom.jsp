<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
    Author     : mette
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="functionLayer.entity.Product"%>
<%@page import="functionLayer.BOMexp"%>
<%@page import="functionLayer.BOM"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <title>Stykliste</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <br>
                    <h1>Styklisteberegning</h1>
                    <br>
                    <br>
                    <%--     <% BOM carportBom = (BOM) session.getAttribute("carportbom");
%>    --%>

                    <% BOMexp carportBomexp = (BOMexp) session.getAttribute("carportbom");
                    %>

                    <div class="col-md-6">

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
                                    ArrayList<Product> bom = carportBomexp.getListOfProducts();
                                    for (Product p : bom) {
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
                    </div>

                    <% if (request.getSession().getAttribute("shedbom") != null) {
                            BOMexp shedBomexp = (BOMexp) session.getAttribute("shedbom");
                    %>
                    <h2>Herunder er styklisten for skuret:</h2>
                    <div class="col-md-6">

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
                                    ArrayList<Product> shedBom = shedBomexp.getListOfProducts();
                                    for (Product p : shedBom) {
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


                    <%-- <table border="1"> 
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th><p>Varetype</th>
                                <th><p>Enhed</th>
                                <th><p>Antal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th><p>Stolper</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getPosts()); %></td>
                            </tr>
                            <tr>
                                <th><p>Understernbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getSubFasciaBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Sternbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getFasciaBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Remme</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getPlates()); %></td>
                            </tr>
                            <tr>
                                <th><p>Spær</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getRafters()); %></td>
                            </tr>
                            <tr>
                                <th><p>Vandbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getWaterBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Tagplader</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getRoof()); %></td>
                            </tr>
                            <tr>
                                <th><p>Skruer til tag</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getRoofScrews()); %></td>
                            </tr>
                            <tr>
                                <th><p>Hulbånd</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(carportBom.getMetalTape()); %></td>
                            </tr>
                            <tr>
                                <th><p>Universalbeslag højre</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getUniBrackets()); %></td>
                            </tr>
                            <tr>
                                <th><p>Universalbeslag venstre</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getUniBrackets()); %></td>
                            </tr>
                            <tr>
                                <th><p>Skruer til stern/vandbræt</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getFasciaScrews()); %></td>
                            </tr>
                            <tr>
                                <th><p>Små skruer t.beslag/hulbånd</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getBracketScrews()); %></td>
                            </tr>
                            <tr>
                                <th><p>Bolte</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getPosts()); %></td>
                            </tr>
                            <tr>
                                <th><p>Firkantskiver</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(carportBom.getPosts() * 2);%></td>
                            </tr>
                        </tbody>
                    </table>

                    <br>
                    
                    
                    
                    --%>
                    <%--
                                        <% if (request.getSession().getAttribute("shedbom") != null) {
                                        %>
                                        <% BOM shedBom = (BOM) session.getAttribute("shedbom");
                                        %>
                                        <h2>Herunder er styklisten for skuret:</h2>
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th><p>Varetype</th>
                                                    <th><p>Enhed</th>
                                                    <th><p>Antal</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th><p>Lægte til dør</th>
                                                    <td><p>Centimeter</td>
                                                    <td><p><%out.print(shedBom.getLath());%></td>
                                                </tr>
                                                <tr>
                                                    <th><p>Reglar til løsholter</th>
                                                    <td><p>Centimeter</td>
                                                    <td><p><%out.print(shedBom.getReglar());%></td>
                                                </tr>
                                                <tr>
                                                    <th><p>Beklædningsbrædder</th>
                                                    <td><p>Centimeter</td>
                                                    <td><p><%out.print(shedBom.getCladding());%></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <%}%>
                    --%>
                </div>
            </div>
        </div>
    </body>
</html>
