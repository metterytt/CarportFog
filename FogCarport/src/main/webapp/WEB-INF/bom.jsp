<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
    Author     : mette
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="functionLayer.entity.Product"%>
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

                    <% BOM carportBOM = (BOM) session.getAttribute("carportbom");
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
                                    ArrayList<Product> bom = carportBOM.getListOfProducts();
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
                            BOM shedBOM = (BOM) session.getAttribute("shedbom");
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
                                    ArrayList<Product> shedBom = shedBOM.getListOfProducts();
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
                </div>
                <div class="col-md-6">
                    <br><h1>Visualization</h1>
                    <br>
                    
                    <% int length = (int)request.getAttribute("length"); 
                       int width = (int)request.getAttribute("width");
                    %>
                    
                    <svg height="<%= length %>" width="<%= width %>">
                    <line x1="0" y1="0" x2="<%= width %>" y2="0" stroke="black"/>
                    <line x1="0" y1="5" x2="<%= width %>" y2="5" stroke="black"/>

                    <text x="210" y="100" fill="black"></text>
                    <line x1="<%= width %>" y1="0" x2="<%= width %>" y2="<%= length %>" stroke="black"/>
                    <line x1="<%= width - 5 %>" y1="0" x2="<%= width - 5 %>" y2="<%= length %>" stroke="black"/>

                    <text x="60" y="220" fill="black"></text>
                    <line x1="<%= width %>" y1="<%= length %>" x2="0" y2="<%= length %>" stroke="black"/>
                    <line x1="<%= width %>" y1="<%= length - 5 %>" x2="0" y2="<%= length - 5 %>" stroke="black"/>

                    <line x1="0" y1="<%= length %>" x2="0" y2="0" stroke="black"/>
                    <line x1="5" y1="<%= length %>" x2="5" y2="0" stroke="black"/>
                    
                    <line x1="0" y1="<%= length - 10 %>" x2="<%= width %>" y2="<%= length - 10 %>" stroke-width="4" stroke="black"/>
                    <% 
                        int startingLength = length - 10;
                        int antalSpær = startingLength/60;
                    
                    for (int idx = 0; idx <= antalSpær; idx++) {
                            %> <line x1="0" y1="<%= startingLength %>" x2="<%= width %>" y2="<%= startingLength %>" stroke-width="4" stroke="black"/> <%
                                startingLength -= 60;
                        }
                    %>
                    </svg>
                </div>
            </div>
        </div>
    </body>
</html>
