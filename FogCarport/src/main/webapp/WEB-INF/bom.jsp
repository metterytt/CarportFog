<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
    Author     : mette
--%>

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
                    <% BOM bom = (BOM) session.getAttribute("bom");
                    %>

                    <%-- <table border="1"> --%>
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
                                <td><p><%out.print(bom.getPosts()); %></td>
                            </tr>
                            <tr>
                                <th><p>Understernbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getSubFasciaBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Sternbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getFasciaBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Remme</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getPlates()); %></td>
                            </tr>
                            <tr>
                                <th><p>Spær</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getRafters()); %></td>
                            </tr>
                            <tr>
                                <th><p>Vandbrædder</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getWaterBoards()); %></td>
                            </tr>
                            <tr>
                                <th><p>Tagplader</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getRoof()); %></td>
                            </tr>
                            <tr>
                                <th><p>Skruer til tag</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getRoofScrews()); %></td>
                            </tr>
                            <tr>
                                <th><p>Hulbånd</th>
                                <td><p>Centimeter</td>
                                <td><p><%out.print(bom.getMetalTape()); %></td>
                            </tr>
                            <tr>
                                <th><p>Universalbeslag højre</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getRafters()); %></td>
                            </tr>
                            <tr>
                                <th><p>Universalbeslag venstre</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getRafters()); %></td>
                            </tr>
                            <tr>
                                <th><p>Små skruer t.beslag/hulbånd</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getSmallScrews()); %></td>
                            </tr>
                            <tr>
                                <th><p>Bolte</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getPosts()); %></td>
                            </tr>
                            <tr>
                                <th><p>Firkantskiver</th>
                                <td><p>Stk.</td>
                                <td><p><%out.print(bom.getPosts() * 2);%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
