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
                    <% BOM carportBom = (BOM) session.getAttribute("carportbom");
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
                </div>
                <div class="col-md-6">
                    <br><h1>Visualization</h1>
                    <br><br>
                    
                    <svg height="220" width="500">
                    <line x1="0" y1="0" x2="200" y2="0" stroke="black"/>
                    <line x1="0" y1="5" x2="200" y2="5" stroke="black"/>

                    <text x="210" y="100" fill="black"></text>
                    <line x1="200" y1="0" x2="200" y2="200" stroke="black"/>
                    <line x1="195" y1="0" x2="195" y2="200" stroke="black"/>

                    <text x="60" y="220" fill="black"></text>
                    <line x1="200" y1="200" x2="0" y2="200" stroke="black"/>
                    <line x1="200" y1="195" x2="0" y2="195" stroke="black"/>

                    <line x1="0" y1="200" x2="0" y2="0" stroke="black"/>
                    <line x1="5" y1="200" x2="5" y2="0" stroke="black"/>

                    <line x1="200" y1="190" x2="0" y2="190" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="162" x2="0" y2="162" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="137" x2="0" y2="137" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="112" x2="0" y2="112" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="87" x2="0" y2="87" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="62" x2="0" y2="62" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="37" x2="0" y2="37" stroke-width="4" stroke="black"/>
                    <line x1="200" y1="10" x2="0" y2="10" stroke-width="4" stroke="black"/>
                    </svg>
                </div>
            </div>
        </div>
    </body>
</html>
