<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="functionLayer.entity.LineItem"%>
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
                <div class="col-md-6">
                    <br><h1>Visualization</h1>
                    <br>

                    <% int length = (int) request.getAttribute("length");
                        int width = (int) request.getAttribute("width");
                        int height = 210;%>


                    <svg height="<%= length + 50%>" width="<%= width + 50%>">

                    <%-- carport set oppefra --%>

                    <%-- <svg height="<%= length + 50%>" width="<%= width + 50%>" viewbox="0 0 700 1000"> --%>


                    <%-- spær --%>
                    <%
                        int startingLength = length - 10;
                        int antalSpær = startingLength / 60;

                        for (int idx = 0; idx <= antalSpær; idx++) {
                    %> <line x1="0" y1="<%= startingLength%>" x2="<%= width%>" y2="<%= startingLength%>" stroke-width="12" stroke="darkgrey"/> <%
                            startingLength -= 60;
                        }
                    %>

                    <%-- stolper --%>
                    <% int posts = 0;
                        for (LineItem li : bom) {
                            if (li.getUseInContext().equals("Nedgraves 90cm i jord")) {
                                posts = li.getQuantity();
                            }
                        }

                        if (posts < 5) {

                    %> <rect x="<%= width - width * 0.9 - 8%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none" />
                    <rect x="<%= width - width * 0.9 - 8%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 8%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - 8%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <%} else {

                    %> <rect x="<%= width - width * 0.9 - 8%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width * 0.9 - 8%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 8%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - 8%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 8%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width * 0.9 - 8%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <%}%>

                    <text x="<%=width / 2%>" y="<%=length + 20%>" fill="black" text-anchor="middle" >Bredde: <%=width%> </text>
                    <text x="<%=width + 20%>" y="<%=length / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Længde: <%=length%> </text>

                    <%-- top stern --%>
                    <line x1="0" y1="0" x2="<%= width%>" y2="0" stroke="black"/>

                    <%-- højre stern --%>
                    <line x1="<%= width%>" y1="0" x2="<%= width%>" y2="<%= length%>" stroke="black"/>
                    <line x1="<%= width - 5%>" y1="0" x2="<%= width - 5%>" y2="<%= length%>" stroke="black"/>

                    <%-- bundstern --%>
                    <line x1="<%= width%>" y1="<%= length%>" x2="0" y2="<%= length%>" stroke="black"/>
                    <line x1="<%= width - 5%>" y1="<%= length - 5%>" x2="5" y2="<%= length - 5%>" stroke="black"/>

                    <%-- venstre stern --%>
                    <line x1="1" y1="<%= length%>" x2="1" y2="0" stroke="black"/>
                    <line x1="5" y1="<%= length%>" x2="5" y2="0" stroke="black"/>

                    <%-- remme --%>
                    <line x1="<%= width * 0.9%>" y1="0" x2="<%= width * 0.9%>" y2="<%= length%>" stroke="lightgrey" stroke-width="12" stroke-opacity = "0.5"/>
                    <line x1="<%= width - width * 0.9%>" y1="0" x2="<%= width - width * 0.9%>" y2="<%= length%>" stroke="lightgrey" stroke-width="12" stroke-opacity = "0.5"/>

                    <%-- hulbånd --%>
                    <line x1="<%= width - width * 0.9%>" y1="50" x2="<%= width * 0.9%>" y2="<%= length - 70%>" stroke="black" stroke-dasharray="5 5"/>
                    <line x1="<%= width - width * 0.9%>" y1="<%= length - 70%>" x2="<%= width * 0.9%>" y2="50" stroke="black" stroke-dasharray="5 5"/>
                    </svg>

                    <%-- Jespers tegning --%>

                    <svg height="<%= length + 50%>" width="<%= width + 50%>">

                    <line x1="0" y1="<%=length%>" x2="<%= width%>" y2="<%=length%>" stroke="black"/> 

                    <line x1="<%= width * 0.3%>" y1="<%=length%>" x2="<%= width * 0.3%>" y2="<%=length * 0.86%>" stroke="black" stroke-width="5"/>
                    <line x1="<%= width * 0.6%>" y1="<%=length%>" x2="<%= width * 0.6%>" y2="<%=length * 0.85%>" stroke="black"stroke-width="5"/>

                    <line x1="<%= width * 0.25%>" y1="<%=length * 0.86%>" x2="<%= width * 0.66%>" y2="<%=length * 0.85%>" stroke="black" stroke-width="5"/>

                    <%-- meassure for back of carport --%>
                    <line x1="<%= width * 0.24%>" y1="<%=length%>" x2="<%= width * 0.24%>" y2="<%=length * 0.86%>" stroke="black"/>
                    <text x="<%= width * 0.17%>" y="<%=length * 0.90%>" fill="black" font-size="10" text-anchor="middle" > 200cm  </text>

                    <%-- meassure for front of carport --%>
                    <line x1="<%= width * 0.72%>" y1="<%=length%>" x2="<%= width * 0.72%>" y2="<%=length * 0.87%>" stroke="black"/>
                    <text x="<%= width * 0.67%>" y="<%=length * 0.9%>" fill="black" font-size="10" text-anchor="middle" > 210cm  </text>

                    <line x1="<%= width * 0.75%>" y1="<%=length%>" x2="<%= width * 0.75%>" y2="<%=length * 0.84%>" stroke="black"/>
                    <text x="<%= width * 0.82%>" y="<%=length * 0.9%>" fill="black" font-size="10" text-anchor="middle" > 225cm  </text>

                    <% if (posts > 4) {%>
                    <line x1="<%= width * 0.45%>" y1="<%=length%>" x2="<%= width * 0.45%>" y2="<%=length * 0.85%>" stroke="black"stroke-width="5"/>
                    <%}%>

                    </svg>

                    <%-- Ende af jespers tegning :D --%>


                    <%-- carport set fra siden Niller --%>
                    <svg height="20cm" width="20cm" viewbox="-25 0 20cm 20cm">

                    <%-- stolper --%>
                    <%
                        if (posts < 5) {
                    %> 

                    <line x1="<%= length - length * 0.8%>" y1="5" x2="<%= length - length * 0.8%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length - length * 0.8 + 10%>" y1="5" x2="<%= length - length * 0.8 + 10%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length * 0.8%>" y1="10" x2="<%= length * 0.8%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length * 0.8 + 10%>" y1="10" x2="<%= length * 0.8 + 10%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <text x="<%=length / 2%>" y="<%=height - 160%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>

                    <%-- rem til 4 stolper--%>
                    <% if (length < 349) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(2 30 40)"/>
                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1 30 40)"/>

                    <% }
                        }
                        if (posts > 4) {
                    %>
                    <line x1="<%= length - length * 0.9%>" y1="5" x2="<%= length - length * 0.9%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length - length * 0.9 + 10%>" y1="5" x2="<%= length - length * 0.9 + 10%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length * 0.9%>" y1="10" x2="<%= length * 0.9%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length * 0.9 + 10%>" y1="10" x2="<%= length * 0.9 + 10%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length / 2 + 5%>" y1="10" x2="<%= length / 2 + 5%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <line x1="<%= length / 2 - 5%>" y1="10" x2="<%= length / 2 - 5%>" y2="<%= height + 10%>" stroke="black" stroke-width="2"/>
                    <text x="<%=length * 0.3%>" y="<%=height - 160%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>

                    <%-- rem til 6 stolper--%>
                    <% if (length < 599) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1 100 50)"/>
                    <% }
                        if (length >= 600) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(0.5 250 50)"/>
                    <% }
                        }%>
                    <line x1="0" y1="<%= height + 10%>" x2="<%= length * 1.30%>" y2="<%= height + 10%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>

                    <polygon points="<%= length + 70%>,15 <%= length + 20%>,170 <%= length + 120%>,170" fill="green" stroke="black" stroke-width="1" />
                    <rect x="<%= length + 60%>" y="170" width="20" height="50" fill="saddlebrown" stroke="black"/>

                    <%-- vil gerne have "højde" til at stå i midten over taget --%> 
                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
                    <text x="<%=length + 15%>" y="<%=height / 2 - 25%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>

                    <%-- tag og stern --%>
                    <line x1="20" y1="5" x2="<%= length%>" y2="13" stroke="black" stroke-width="5"/>
                    <line x1="20" y1="10" x2="<%= length%>" y2="18" stroke="darkgrey" stroke-width="5"/>

                    <%-- streg for hældning --%>
                    <line x1="20" y1="0" x2="<%= length%>" y2="0" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
                    <line x1="<%= length%>" y1="0" x2="<%= length%>" y2="<%= 15%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>

                    </svg>
                </div>
            </div>
        </div>
    </body>
</html>
