<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
--%>

<%@page import="functionLayer.DrawingMeasures"%>
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

        <form name="login" action="FrontController" method="post">
            <input type="hidden" name="command" value="sendrequest">
            <br/>
            <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
        </form>

        <div class="container-fluid">
            <div class="row">



                <%--<% BOM carportBOM = (BOM) session.getAttribute("carportbom");
                    BOM shedBOM = (BOM) request.getSession().getAttribute("shedbom");
                    ArrayList<LineItem> bom = carportBOM.getListOfProducts();
                %> --%>



                <div class="col-md-6">
                    <br><h1>Visualization</h1>
                    <%
                        DrawingMeasures drawingMeasures = (DrawingMeasures) session.getAttribute("drawingmeasures");
                        int length = drawingMeasures.getLength();
                        int width = drawingMeasures.getWidth();
                        int height = drawingMeasures.getHeight();
                        int angle = drawingMeasures.getAngle();
                        int shedLength = drawingMeasures.getShedLength();
                        int shedWidth = drawingMeasures.getShedWidth();

                        String shedPos = (String) request.getAttribute("shedPos"); %>  <%-- vær opmærksom her --%>
                    <%   double rafterGap = drawingMeasures.getRafterGap();
                        int rafterQty = drawingMeasures.getRafterQty();
                        int posts = drawingMeasures.getPosts();
                        double startingLength = (length - 10);
                    %>

                    <%--
                    <%  int length = carportBOM.getLength();
                        int width = carportBOM.getWidth();
                        int height = 210;
                        int angle = carportBOM.getAngle();
                        int shedLength = 0;
                        int shedWidth = 0;
                        if (shedBOM != null) {
                            shedLength = shedBOM.getShedLength();
                            shedWidth = shedBOM.getWidth();
                        }
                        String shedPos = (String) request.getAttribute("shedPos");

                    %>
                    --%>


                    <%-- carport set oppefra --%>
                    <svg height="500" width="500" viewbox="0 0 <%= width + 150%> <%= length + 60%>">

                    <%-- remme --%>
                    <line x1="<%= width * 0.9%>" y1="0" x2="<%= width * 0.9%>" y2="<%= length%>" stroke="black" stroke-width="12" stroke-opacity = "0.5"/>
                    <line x1="<%= width - width * 0.9%>" y1="0" x2="<%= width - width * 0.9%>" y2="<%= length%>" stroke="black" stroke-width="12" stroke-opacity = "0.5"/>

                    <%-- spær --%>
                    <%--
                    
                        
                        double rafterGap = (double) request.getAttribute("rafterGap");
                        int rafterQuantity = (int) request.getAttribute("rafterQuantity");
                        double startingLength = (length - 10);
                    --%>
                    <%
                        for (int idx = 0; idx < rafterQty; idx++) {
                    %> <line x1="5" y1="<%= startingLength%>" x2="<%= width - 5%>" y2="<%= startingLength%>" stroke-width="12" stroke="darkgrey"/> <%
                            startingLength -= rafterGap;
                        }
                    %>

                    <%-- stolper --%>
                    <%--
                    <% int posts = 0;
                        for (LineItem li : bom) {
                            if (li.getUseInContext().equals("Nedgraves 90cm i jord")) {
                                posts = li.getQuantity();
                            }
                        }
                    --%>
                    <%  if (posts < 5) {

                    %> <rect x="<%= width - width * 0.9 - 4%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none" />
                    <rect x="<%= width - width * 0.9 - 4%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 11%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - 11%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <%} else {

                    %> <rect x="<%= width - width * 0.9 - 4%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width * 0.9 - 4%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 11%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - 11%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width * 0.9 - 11%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width * 0.9 - 4%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <%}%>

                    <text x="<%=width / 2%>" y="<%=length + 20%>" fill="black" text-anchor="middle" >Bredde: <%=width%> </text>
                    <text x="<%=width + 20%>" y="<%=length / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Længde: <%=length%> </text>

                    <%-- top stern --%>
                    <line x1="0" y1="0" x2="<%= width%>" y2="0" stroke-width="8" stroke="black"/>

                    <%-- højre stern --%>
                    <line x1="<%= width%>" y1="0" x2="<%= width%>" y2="<%= length%>" stroke-width="4" stroke="black"/>
                    <line x1="<%= width - 5%>" y1="0" x2="<%= width - 5%>" y2="<%= length%>" stroke-width="4" stroke="black"/>

                    <%-- bundstern --%>
                    <line x1="<%= width%>" y1="<%= length%>" x2="0" y2="<%= length%>" stroke-width="4" stroke="black"/>
                    <line x1="<%= width - 5%>" y1="<%= length - 5%>" x2="5" y2="<%= length - 5%>" stroke-width="4" stroke="black"/>

                    <%-- venstre stern --%>
                    <line x1="0" y1="<%= length%>" x2="0" y2="0" stroke-width="4" stroke="black"/>
                    <line x1="5" y1="<%= length%>" x2="5" y2="0" stroke-width="4" stroke="black"/>

                    <%-- hulbånd --%>
                    <line x1="<%= width - width * 0.9%>" y1="<%= rafterGap + 10%>" x2="<%= width * 0.9%>" y2="<%= (length - 10) - rafterGap%>" stroke="black" stroke-dasharray="5 5"/>
                    <line x1="<%= width - width * 0.9%>" y1="<%= (length - 10) - rafterGap%>" x2="<%= width * 0.9%>" y2="<%= rafterGap + 10%>" stroke="black" stroke-dasharray="5 5"/>


                    <% if (shedLength != 0 && shedWidth != 0 && shedPos.equals("left")) {%>

                    <%-- Skur VENSTRE --%>


                    <line x1= "<%= width - width * 0.9%>" y1="<%= length - length * 0.95%>" x2="<%= (width - width * 0.9) + shedWidth%>" y2="<%= length - length * 0.95%>" stroke-width="4" stroke="red"/>

                    <line x1= "<%= width - width * 0.9%>" y1="<%= length - length * 0.95%>" x2="<%= width - width * 0.9%>" y2="<%= shedLength%>" stroke-width="4" stroke="red"/>

                    <line x1= "<%= width - width * 0.9%>" y1="<%= shedLength%>" x2="<%= (width - width * 0.9) + shedWidth%>" y2="<%= shedLength%>" stroke-width="4" stroke="red"/>

                    <line x1= "<%= (width - width * 0.9) + shedWidth%>" y1="<%= shedLength%>" x2="<%= (width - width * 0.9) + shedWidth%>" y2="<%= length - length * 0.95%>" stroke-width="4" stroke="red"/>


                    <rect x="<%= width - width * 0.9%>" y="<%= length - length * 0.95%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width * 0.9%>" y="<%= shedLength - 15%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= (width - width * 0.9) - 15 + shedWidth%>" y="<%= shedLength - 15%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>

                    <rect x="<%= (width - width * 0.9) + shedWidth - 15%>" y="<%= length - length * 0.95%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>

                    <%} else if (shedLength != 0 && shedWidth != 0) {%>

                    <%-- Skur HØJRE --%>
                    <line x1= "<%= width * 0.9%>" y1="<%= length - length * 0.95%>" x2="<%= width * 0.9 - shedWidth%>" y2="<%= length - length * 0.95%>" stroke-width="4" stroke="red"/>
                    <line x1= "<%= width * 0.9%>" y1="<%= length - length * 0.95%>" x2="<%= width * 0.9%>" y2="<%= shedLength%>" stroke-width="4" stroke="red"/>
                    <line x1= "<%= width * 0.9%>" y1="<%= shedLength%>" x2="<%= width * 0.9 - shedWidth%>" y2="<%= shedLength%>" stroke-width="4" stroke="red"/>
                    <line x1= "<%= width * 0.9 - shedWidth%>" y1="<%= shedLength%>" x2="<%= width * 0.9 - shedWidth%>" y2="<%= length - length * 0.95%>" stroke-width="4" stroke="red"/>

                    <%-- Skur Stolper i hjørner, ikke inkl dør. --%>
                    <rect x="<%= width * 0.9 - 15%>" y="<%= length - length * 0.95%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - 15%>" y="<%= shedLength - 15%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - shedWidth%>" y="<%= shedLength - 15%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width * 0.9 - shedWidth%>" y="<%= length - length * 0.95%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>

                    <%--
                    <rect x="<%= (width - width * 0.9) + shedWidth - 15%>" y="<%= length - length * 0.95%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    --%>


                    <%}%>

                    </svg>















                    <%-- carport set fra siden stor tegning/Nillers tegning --%>

                    <% if (angle == 0) {%>

                    <svg width="600" height="500" viewbox="-10 -80 <%= width * 2.5%> <%= length%>"> 

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
                          transform="translate(0) rotate(1.7 30 40)"/>
                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1.05 0 15)"/>

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

                    <%-- stiplet linje til jorden --%>
                    <line x1="0" y1="<%= height + 10%>" x2="<%= length * 1.30%>" y2="<%= height + 10%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>

                    <%-- træ --%>
                    <polygon points="<%= length + 70%>,15 <%= length + 20%>,170 <%= length + 120%>,170" fill="green" stroke="black" stroke-width="1" />
                    <rect x="<%= length + 60%>" y="170" width="20" height="50" fill="saddlebrown" stroke="black"/>

                    <%-- vil gerne have "højde" til at stå i midten over taget --%> 
                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
                    <text x="<%=length + 15%>" y="<%=height / 2 - 25%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>

                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= length%>" y2="18" stroke="darkgrey" stroke-width="3"/>
                    <line x1="20" y1="5" x2="<%= length%>" y2="13" stroke="black" stroke-width="8"/>

                    <%-- streg for hældning --%>
                    <line x1="20" y1="0" x2="<%= length%>" y2="0" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
                    <line x1="<%= length%>" y1="0" x2="<%= length%>" y2="<%= 15%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>


                    </svg>

                    <% } %>




















                    <% if (angle > 0) {%>
                    <%-- carport med rejsning set fra siden stor tegning/Nillers tegning --%>

                    <svg width="600" height="500" viewbox="-10 -80 <%= width * 2.5%> <%= length%>"> 


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
                          transform="translate(0) rotate(1.7 30 40)"/>
                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1.05 0 15)"/>

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
                        if (length >= 60) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(0.5 250 50)"/>
                    <% }
                        }%>


                    <%-- stiplet linje til jorden --%>
                    <line x1="0" y1="<%= height + 10%>" x2="<%= length * 1.30%>" y2="<%= height + 10%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>


                    <%-- træ --%>
                    <polygon points="<%= length + 70%>,15 <%= length + 20%>,170 <%= length + 120%>,170" fill="green" stroke="black" stroke-width="1" />
                    <rect x="<%= length + 60%>" y="170" width="20" height="50" fill="saddlebrown" stroke="black"/>

                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
                    <text x="<%=length + 15%>" y="<%=height / 2 - 100%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>


                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= length%>" y2="18" stroke="darkgrey" stroke-width="3"/>
                    <line x1="20" y1="5" x2="<%= length%>" y2="13" stroke="black" stroke-width="8"/>


                    <%-- tag med rejsning --%>
                    <%--streg for hældning--%>
                    <line x1="20" y1="<%= height - height - 75%>" x2="<%= length%>" y2="<%= height - height - 75%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
                    <line x1="<%= length%>" y1="<%= height - height - 75%>" x2="<%= length%>" y2="<%= height - height - 63%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>


                    <%-- spær --%>

                    <%
                        if (posts < 5) {
                    %>
                    <%-- spær i toppen til 4 stolper--%>
                    <% if (length < 349) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1.5 40 10)"/>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(0.7 0 35)"/>
                    <rect x="<%= length - 13%>" y="<%=height - height - 66%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(1 330 135)"/>

                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= length - 13%>" y="<%=height - height - 68%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(1 330 135)"/>

                    <% }
                        }%>


                    <%-- spær i toppen til 6 stolper--%>
                    <%
                        if (posts > 4) {
                    %>

                    <% if (length < 599) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= length - 13%>" y="<%=height - height - 70%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(1 330 135)"/>
                    <% }
                        if (length >= 600) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= length - 13%>" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(1 330 135)"/>
                    <% }
                        }%>

                    <defs>

                    <pattern id="pattern"
                             width="15" height="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="darkgrey" stroke-width="3px" y2="10"/>
                    </pattern>

                    </defs>

                    <rect x="31" y="<%=height - height - 59%>"
                          width="<%= length - 42%>" height="62"
                          fill= "url(#pattern)"
                          stroke="none"
                          stroke-width="2px" 
                          transform="translate(0) rotate(1.2 80 45)"/>

                    </svg>







                    <%-- carport set forfra med rejsning --%>
                    <svg width="600" height="500" viewbox="-10 -80 <%= width * 2.5%> <%= length%>"> 

                    <%-- stolper --%>
                    <rect x="40" y="<%=height - height%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= width%>" y="<%=height - height%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>


                    <%-- over- og understern --%>
                    <rect x="15" y="-10" width="<%= width + 20%>" height="10" fill="snow" stroke="black"/>


                    <%-- gavl --%>
                    <%
                        double calcAngle = Math.toRadians(angle);
                        double gableHeight = (width / 2) * Math.tan(calcAngle);
                    %>
                    <rect x="<%= width / 2 + 20%>" y="<%= -10 - gableHeight%>" width="10" height="<%= gableHeight%>" fill="snow" stroke="black" stroke-width="1"/>

                    <%-- vindskede/stern --%>
                    <%
                        calcAngle = Math.toRadians(angle);
                        double fasciaBoards = ((width / 2) / Math.cos(calcAngle));
                    %>

                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> 15,-5 15,-15" 
                             fill="snow" stroke="black"/>
                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> <%= width + 35%>,<%= -5%> <%= width + 35%>,<%= -15%>" 
                             fill="snow" stroke="black"/>

                    <%-- 
                    <rect x="<%= width / 2 + 20%>" y="<%= -height * 0.5%>" width="10" height="<%= fasciaBoards + 15%>" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(68 131 -84)"/>
                    <rect x="<%= width / 2 + 20%>" y="<%= -height * 0.5%>" width="10" height="<%= fasciaBoards + 15%>" fill="snow" stroke="black" stroke-width="1"
                          transform="translate(0) rotate(-68 189 -84)"/>
                    --%>

                    <%-- streg for højde med tag --%>
                    <line x1="<%= width + 50%>" y1="<%= -20 - gableHeight%>" x2="<%= width + 50%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 60%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde med tag: <%=height + (int) gableHeight%> </text>

                    <%-- streg for højde uden tag --%>
                    <line x1="<%= width + 80%>" y1="-15" x2="<%= width + 80%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 90%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde uden tag: <%=height + (int) gableHeight%> </text>


                    <%-- streg langs jorden --%>
                    <line x1="0" y1="<%= height%>" x2="<%= width + 150%>" y2="<%= height%>" stroke="black"/>



                    </svg>

                    <% }%>

                </div>
            </div>
        </div>



    </body>
</html>
