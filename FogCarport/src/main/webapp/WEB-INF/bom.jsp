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
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>

        <form action="FrontController" method="post">
            <input type="hidden" name="command" value="sendrequest">
            <br/>
            <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
        </form>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <br><h1>Visualization</h1>
                    <%
                        DrawingMeasures drawingMeasures = (DrawingMeasures) request.getAttribute("drawingmeasures");
<<<<<<< HEAD
                        int lengthDraw = drawingMeasures.getLength();
                        int widthDraw = drawingMeasures.getWidth();
                        int heightDraw = drawingMeasures.getHeight();
                        int angleDraw = drawingMeasures.getAngle();
                        int shedLengthDraw = drawingMeasures.getShedLength();
                        int shedWidthDraw = drawingMeasures.getShedWidth();
=======
                        int length = drawingMeasures.getLength();
                        int width = drawingMeasures.getWidth();
                        int height = drawingMeasures.getHeight();
                        int angle = drawingMeasures.getAngle();
                        int shedLength = drawingMeasures.getShedLength();
                        int shedWidth = drawingMeasures.getShedWidth();
>>>>>>> 1727dba1bc5ddfbbf541e131dbad141d84033057

                        String shedPos = (String) request.getAttribute("shedPos"); %>  <%-- vær opmærksom her --%>
                    <%   double rafterGap = drawingMeasures.getRafterGap();
                        int rafterQty = drawingMeasures.getRafterQty();
                        int posts = drawingMeasures.getPosts();
                        double startingLength = (lengthDraw - 10);
                    %>

                    <%-- carport set oppefra --%>
                    <svg heightDraw="500" widthDraw="500" viewbox="0 0 <%= widthDraw + 150%> <%= lengthDraw + 60%>">

                    <%-- remme --%>
                    <line x1="<%= widthDraw * 0.9%>" y1="0" x2="<%= widthDraw * 0.9%>" y2="<%= lengthDraw%>" stroke="black" stroke-widthDraw="12" stroke-opacity = "0.5"/>
                    <line x1="<%= widthDraw - widthDraw * 0.9%>" y1="0" x2="<%= widthDraw - widthDraw * 0.9%>" y2="<%= lengthDraw%>" stroke="black" stroke-widthDraw="12" stroke-opacity = "0.5"/>

                    <%-- spær --%>
                    <%
                        for (int idx = 0; idx < rafterQty; idx++) {
                    %> <line x1="5" y1="<%= startingLength%>" x2="<%= widthDraw - 5%>" y2="<%= startingLength%>" stroke-widthDraw="12" stroke="darkgrey"/> <%
                            startingLength -= rafterGap;
                        }
                    %>

                    <%-- stolper --%>
                    <%  if (posts < 5) {

                    %> <rect x="<%= widthDraw - widthDraw * 0.9 - 4%>" y="<%=lengthDraw * 0.25%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none" />
                    <rect x="<%= widthDraw - widthDraw * 0.9 - 4%>" y="<%=lengthDraw * 0.75%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>

                    <rect x="<%= widthDraw * 0.9 - 11%>" y="<%=lengthDraw * 0.25%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw * 0.9 - 11%>" y="<%=lengthDraw * 0.75%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>
                    <%} else {

                    %> <rect x="<%= widthDraw - widthDraw * 0.9 - 4%>" y="<%=lengthDraw * 0.1%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw - widthDraw * 0.9 - 4%>" y="<%=lengthDraw * 0.9%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>

                    <rect x="<%= widthDraw * 0.9 - 11%>" y="<%=lengthDraw * 0.1%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw * 0.9 - 11%>" y="<%=lengthDraw * 0.9%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>

                    <rect x="<%= widthDraw * 0.9 - 11%>" y="<%=lengthDraw / 2%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw - widthDraw * 0.9 - 4%>" y="<%=lengthDraw / 2%>" heightDraw="15" widthDraw="15" stroke="black" stroke-widthDraw="3" fill="none"/>

                    <%}%>

                    <text x="<%=widthDraw / 2%>" y="<%=lengthDraw + 20%>" fill="black" text-anchor="middle" >Bredde: <%=widthDraw%> </text>
                    <text x="<%=widthDraw + 20%>" y="<%=lengthDraw / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Længde: <%=lengthDraw%> </text>

                    <%-- top stern --%>
                    <line x1="0" y1="0" x2="<%= widthDraw%>" y2="0" stroke-widthDraw="8" stroke="black"/>

                    <%-- højre stern --%>
                    <line x1="<%= widthDraw%>" y1="0" x2="<%= widthDraw%>" y2="<%= lengthDraw%>" stroke-widthDraw="4" stroke="black"/>
                    <line x1="<%= widthDraw - 5%>" y1="0" x2="<%= widthDraw - 5%>" y2="<%= lengthDraw%>" stroke-widthDraw="4" stroke="black"/>

                    <%-- bundstern --%>
                    <line x1="<%= widthDraw%>" y1="<%= lengthDraw%>" x2="0" y2="<%= lengthDraw%>" stroke-widthDraw="4" stroke="black"/>
                    <line x1="<%= widthDraw - 5%>" y1="<%= lengthDraw - 5%>" x2="5" y2="<%= lengthDraw - 5%>" stroke-widthDraw="4" stroke="black"/>

                    <%-- venstre stern --%>
                    <line x1="0" y1="<%= lengthDraw%>" x2="0" y2="0" stroke-widthDraw="4" stroke="black"/>
                    <line x1="5" y1="<%= lengthDraw%>" x2="5" y2="0" stroke-widthDraw="4" stroke="black"/>

                    <%-- hulbånd --%>
                    <line x1="<%= widthDraw - widthDraw * 0.9%>" y1="<%= rafterGap + 10%>" x2="<%= widthDraw * 0.9%>" y2="<%= (lengthDraw - 10) - rafterGap%>" stroke="black" stroke-dasharray="5 5"/>
                    <line x1="<%= widthDraw - widthDraw * 0.9%>" y1="<%= (lengthDraw - 10) - rafterGap%>" x2="<%= widthDraw * 0.9%>" y2="<%= rafterGap + 10%>" stroke="black" stroke-dasharray="5 5"/>


                    <% if (shedLengthDraw != 0 && shedWidthDraw != 0 && shedPos.equals("left")) {%>


                    <%-- Skur VENSTRE --%>
                    <line x1= "<%= widthDraw - widthDraw * 0.9%>" y1="<%= lengthDraw - lengthDraw * 0.95%>" x2="<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw%>" y2="<%= lengthDraw - lengthDraw * 0.95%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= widthDraw - widthDraw * 0.9%>" y1="<%= lengthDraw - lengthDraw * 0.95%>" x2="<%= widthDraw - widthDraw * 0.9%>" y2="<%= shedLengthDraw%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= widthDraw - widthDraw * 0.9%>" y1="<%= shedLengthDraw%>" x2="<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw%>" y2="<%= shedLengthDraw%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw%>" y1="<%= shedLengthDraw%>" x2="<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw%>" y2="<%= lengthDraw - lengthDraw * 0.95%>" stroke-widthDraw="4" stroke="red"/>


                    <rect x="<%= widthDraw - widthDraw * 0.9%>" y="<%= lengthDraw - lengthDraw * 0.95%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw - widthDraw * 0.9%>" y="<%= shedLengthDraw - 15%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= (widthDraw - widthDraw * 0.9) - 15 + shedWidthDraw%>" y="<%= shedLengthDraw - 15%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw - 15%>" y="<%= lengthDraw - lengthDraw * 0.95%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>

                    <%} else if (shedLengthDraw != 0 && shedWidthDraw != 0) {%>

                    <%-- Skur HØJRE --%>
                    <line x1= "<%= widthDraw * 0.9%>" y1="<%= lengthDraw - lengthDraw * 0.95%>" x2="<%= widthDraw * 0.9 - shedWidthDraw%>" y2="<%= lengthDraw - lengthDraw * 0.95%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= widthDraw * 0.9%>" y1="<%= lengthDraw - lengthDraw * 0.95%>" x2="<%= widthDraw * 0.9%>" y2="<%= shedLengthDraw%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= widthDraw * 0.9%>" y1="<%= shedLengthDraw%>" x2="<%= widthDraw * 0.9 - shedWidthDraw%>" y2="<%= shedLengthDraw%>" stroke-widthDraw="4" stroke="red"/>
                    <line x1= "<%= widthDraw * 0.9 - shedWidthDraw%>" y1="<%= shedLengthDraw%>" x2="<%= widthDraw * 0.9 - shedWidthDraw%>" y2="<%= lengthDraw - lengthDraw * 0.95%>" stroke-widthDraw="4" stroke="red"/>

                    <%-- Skur Stolper i hjørner, ikke inkl dør. --%>
                    <rect x="<%= widthDraw * 0.9 - 15%>" y="<%= lengthDraw - lengthDraw * 0.95%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw * 0.9 - 15%>" y="<%= shedLengthDraw - 15%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw * 0.9 - shedWidthDraw%>" y="<%= shedLengthDraw - 15%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    <rect x="<%= widthDraw * 0.9 - shedWidthDraw%>" y="<%= lengthDraw - lengthDraw * 0.95%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>

                    <%--
                    <rect x="<%= (widthDraw - widthDraw * 0.9) + shedWidthDraw - 15%>" y="<%= lengthDraw - lengthDraw * 0.95%>" heightDraw="15" widthDraw="15" stroke="red" stroke-widthDraw="3" fill="none"/>
                    --%>

                    <%}%>

                    </svg>














                    <%-- carport set fra siden med fladt tag --%>

                    <%-- checker om der er fladt tag --%>
                    <% if (angleDraw == 0) {%>

                    <%-- <svg widthDraw="600" heightDraw="500" viewbox="-10 -80 <%= widthDraw * 2.5%> <%= lengthDraw%>"> --%>
                    <svg widthDraw="1000" heightDraw="1000" viewbox="-200 -200 <%= widthDraw * 3%> <%= lengthDraw * 2%>"> 


                    <%-- stolper --%>
                    <%
                        if (posts < 5) {
                    %> 

                    <rect x="50" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw - 40%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <%-- tekst til længde --%>
                    <% if (shedLengthDraw == 0) {%>
                    <text x="<%=lengthDraw / 2%>" y="<%=heightDraw - 230%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <% if (shedLengthDraw != 0) {%>
                    <text x="<%=lengthDraw / 2%>" y="<%=heightDraw - 230%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <%-- rem til 4 stolper--%>
                    <% if (lengthDraw < 349) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1.7 30 40)"/>
                    <% }
                        if (lengthDraw >= 350) {
                    %>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1.05 0 15)"/>

                    <% }
                        }
                        if (posts > 4) {
                    %>

                    <rect x="50" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw / 2%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw - 40%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <%-- tekst til længde --%>
                    <% if (shedLengthDraw == 0) {%>
                    <text x="<%=lengthDraw / 2%>" y="<%=heightDraw - 230%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <% if (shedLengthDraw != 0) {%>
                    <text x="<%=lengthDraw / 2%>" y="<%=heightDraw - 230%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <%-- rem til 6 stolper--%>
                    <% if (lengthDraw < 599) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1 100 50)"/>
                    <% }
                        if (lengthDraw >= 600) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(0.5 250 50)"/>
                    <% }
                        }%>

                    <%-- stiplet linje til jorden --%>
                    <line x1="0" y1="<%= heightDraw + 10%>" x2="<%= lengthDraw * 1.30%>" y2="<%= heightDraw + 10%>" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>

                    <%-- træ --%>
                    <polygon points="<%= lengthDraw + 70%>,15 <%= lengthDraw + 20%>,170 <%= lengthDraw + 120%>,170" fill="green" stroke="black" stroke-widthDraw="1" />
                    <rect x="<%= lengthDraw + 60%>" y="170" widthDraw="20" heightDraw="50" fill="saddlebrown" stroke="black"/>

                    <%-- vil gerne have "højde" til at stå i midten over taget --%> 
                    <text x="<%=lengthDraw * 0.05%>" y="<%=heightDraw / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=heightDraw%> </text>
                    <text x="<%=lengthDraw + 15%>" y="<%=heightDraw / 2 - 25%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>

                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= lengthDraw%>" y2="18" stroke="darkgrey" stroke-widthDraw="3"/>
                    <line x1="20" y1="5" x2="<%= lengthDraw%>" y2="13" stroke="black" stroke-widthDraw="8"/>

                    <%-- streg for hældning --%>
                    <line x1="20" y1="0" x2="<%= lengthDraw%>" y2="0" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>
                    <line x1="<%= lengthDraw%>" y1="0" x2="<%= lengthDraw%>" y2="<%= 15%>" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>

                    <%-- pattern med striber til skur --%>
                    <defs>
                    <pattern id="patternSkur"
                             widthDraw="15" heightDraw="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="black" stroke-widthDraw="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- checker om der er skur (fladt tag) --%>
                    <% if (shedLengthDraw != 0) {%>

                    <% if (lengthDraw < 600) {%>
                    <polygon points="<%=lengthDraw - shedLengthDraw - 40%>,<%=27%> <%=lengthDraw - 40%>,<%=33%> <%= lengthDraw - 40%>,<%= heightDraw + 10%> <%= lengthDraw - shedLengthDraw - 40%>,<%= heightDraw + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% } %>
                    <% if (lengthDraw >= 600) {%>
                    <polygon points="<%=lengthDraw - shedLengthDraw - 40%>,<%=25%> <%=lengthDraw - 40%>,<%=27%> <%= lengthDraw - 40%>,<%= heightDraw + 10%> <%= lengthDraw - shedLengthDraw - 40%>,<%= heightDraw + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% }
                        }%>

                    </svg>

                    <% } %>




















                    <%-- checker om der er hældning på taget --%>
                    <% if (angleDraw > 0) {%>

                    <%-- carport med rejsning set fra siden stor tegning/Nillers tegning --%>

                    <%-- <svg widthDraw="600" heightDraw="500" viewbox="-10 -80 <%= widthDraw * 2.5%> <%= lengthDraw%>"> --%>
                    <svg widthDraw="1000" heightDraw="1000" viewbox="-200 -200 <%= widthDraw * 3%> <%= lengthDraw * 2%>"> 


                    <%-- stolper --%>
                    <%
                        if (posts < 5) {
                    %> 

                    <rect x="50" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw - 40%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <% if (shedLengthDraw == 0) {%>
                    <text x="<%=lengthDraw / 2%>" y="<%=heightDraw - 160%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <% if (shedLengthDraw != 0) {%>
                    <text x="<%=lengthDraw * 0.3%>" y="<%=heightDraw - 160%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>
                    <% } %>

                    <%-- rem til 4 stolper--%>
                    <% if (lengthDraw < 349) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1.7 30 40)"/>
                    <% }
                        if (lengthDraw >= 350) {
                    %>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1.05 0 15)"/>

                    <% }
                        }
                        if (posts > 4) {
                    %>

                    <rect x="50" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw / 2%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= lengthDraw - 40%>" y="<%=10%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <text x="<%=lengthDraw * 0.3%>" y="<%=heightDraw - 160%>" fill="black" text-anchor="middle">Længde: <%=lengthDraw%> </text>

                    <%-- rem til 6 stolper--%>
                    <% if (lengthDraw < 599) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1 100 50)"/>
                    <% }
                        if (lengthDraw >= 60) {%>
                    <rect x="20" y="12" widthDraw="<%=lengthDraw - 20%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(0.5 250 50)"/>
                    <% }
                        }%>

                    <%-- stiplet linje til jorden --%>
                    <line x1="0" y1="<%= heightDraw + 10%>" x2="<%= lengthDraw * 1.30%>" y2="<%= heightDraw + 10%>" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>

                    <%-- træ --%>
                    <polygon points="<%= lengthDraw + 70%>,15 <%= lengthDraw + 20%>,170 <%= lengthDraw + 120%>,170" fill="green" stroke="black" stroke-widthDraw="1" />
                    <rect x="<%= lengthDraw + 60%>" y="170" widthDraw="20" heightDraw="50" fill="saddlebrown" stroke="black"/>

                    <%-- Tekst til højde og hældning --%>
                    <text x="<%=lengthDraw * 0.05%>" y="<%=heightDraw / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=heightDraw%> </text>
                    <text x="<%=lengthDraw + 15%>" y="<%=heightDraw / 2 - 100%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>

                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= lengthDraw%>" y2="18" stroke="darkgrey" stroke-widthDraw="3"/>
                    <line x1="20" y1="5" x2="<%= lengthDraw%>" y2="13" stroke="black" stroke-widthDraw="8"/>

                    <%-- tag med rejsning --%>
                    <%--streg for hældning--%>
                    <line x1="20" y1="<%= heightDraw - heightDraw - 75%>" x2="<%= lengthDraw%>" y2="<%= heightDraw - heightDraw - 75%>" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>
                    <line x1="<%= lengthDraw%>" y1="<%= heightDraw - heightDraw - 75%>" x2="<%= lengthDraw%>" y2="<%= heightDraw - heightDraw - 63%>" stroke="black" stroke-widthDraw="2" stroke-dasharray="5 5"/>

                    <%-- spær --%>
                    <%
                        if (posts < 5) {
                    %>
                    <%-- spær i toppen til 4 stolper--%>
                    <% if (lengthDraw < 349) {%>
                    <rect x="30" y="<%=heightDraw - heightDraw - 70%>" widthDraw="<%=lengthDraw - 42%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1.5 40 10)"/>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=heightDraw - heightDraw - 75%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(0.7 0 35)"/>
                    <rect x="<%= lengthDraw - 13%>" y="<%=heightDraw - heightDraw - 66%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(1 330 135)"/>

                    <% }
                        if (lengthDraw >= 350) {
                    %>
                    <rect x="30" y="<%=heightDraw - heightDraw - 70%>" widthDraw="<%=lengthDraw - 42%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=heightDraw - heightDraw - 75%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= lengthDraw - 13%>" y="<%=heightDraw - heightDraw - 68%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(1 330 135)"/>

                    <% }
                        }%>

                    <%-- spær i toppen til 6 stolper--%>
                    <%
                        if (posts > 4) {
                    %>

                    <% if (lengthDraw < 599) {%>
                    <rect x="30" y="<%=heightDraw - heightDraw - 70%>" widthDraw="<%=lengthDraw - 42%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=heightDraw - heightDraw - 75%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= lengthDraw - 13%>" y="<%=heightDraw - heightDraw - 70%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(1 330 135)"/>
                    <% }
                        if (lengthDraw >= 600) {%>
                    <rect x="30" y="<%=heightDraw - heightDraw - 70%>" widthDraw="<%=lengthDraw - 42%>" heightDraw="10" fill="snow" stroke="black" stroke-widthDraw="1" 
                          transform="translate(0) rotate(1 80 40)"/>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=heightDraw - heightDraw - 75%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(0.5 0 35)"/>
                    <rect x="<%= lengthDraw - 13%>" y="<%=heightDraw - heightDraw - 75%>" widthDraw="10" heightDraw="76" fill="snow" stroke="black" stroke-widthDraw="1"
                          transform="translate(0) rotate(1 330 135)"/>
                    <% }
                        }%>

                    <%-- pattern med striber til tag. --%>
                    <defs>
                    <pattern id="pattern"
                             widthDraw="15" heightDraw="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="darkgrey" stroke-widthDraw="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- Lodrette striper på tag --%>
                    <rect x="31" y="<%=heightDraw - heightDraw - 59%>"
                          widthDraw="<%= lengthDraw - 42%>" heightDraw="62"
                          fill= "url(#pattern)"
                          stroke="none"
                          stroke-widthDraw="2px" 
                          transform="translate(0) rotate(1.2 80 45)"/>

                    <%-- pattern med striber til skur --%>
                    <defs>
                    <pattern id="patternSkur"
                             widthDraw="15" heightDraw="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="black" stroke-widthDraw="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- checker om der er skur (fladt tag) --%>
                    <% if (shedLengthDraw != 0) {%>

                    <% if (lengthDraw < 600) {%>
                    <polygon points="<%=lengthDraw - shedLengthDraw - 40%>,<%=27%> <%=lengthDraw - 40%>,<%=33%> <%= lengthDraw - 40%>,<%= heightDraw + 10%> <%= lengthDraw - shedLengthDraw - 40%>,<%= heightDraw + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% } %>
                    <% if (lengthDraw >= 600) {%>
                    <polygon points="<%=lengthDraw - shedLengthDraw - 40%>,<%=25%> <%=lengthDraw - 40%>,<%=27%> <%= lengthDraw - 40%>,<%= heightDraw + 10%> <%= lengthDraw - shedLengthDraw - 40%>,<%= heightDraw + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% }
                        }%>
                    </svg>







                    <%-- carport set forfra med rejsning --%>
                    <%-- <svg widthDraw="600" heightDraw="500" viewbox="-10 -80 <%= widthDraw * 3%> <%= lengthDraw%>"> --%>
                    <svg widthDraw="1000" heightDraw="1000" viewbox="-200 -200 <%= widthDraw * 3%> <%= lengthDraw * 2%>"> 

                    <%-- stolper --%>
                    <rect x="35" y="<%=heightDraw - heightDraw%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>
                    <rect x="<%= widthDraw + 5%>" y="<%=heightDraw - heightDraw%>" widthDraw="10" heightDraw="<%= heightDraw%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <%-- over- og understern --%>
                    <rect x="15" y="-10" widthDraw="<%= widthDraw + 20%>" heightDraw="10" fill="snow" stroke="black"/>

                    <%-- gavl --%>
                    <%
                        double calcAngle = Math.toRadians(angleDraw);
                        double gableHeight = (widthDraw / 2) * Math.tan(calcAngle);
                    %>
                    <rect x="<%= widthDraw / 2 + 20%>" y="<%= -10 - gableHeight%>" widthDraw="10" heightDraw="<%= gableHeight%>" fill="snow" stroke="black" stroke-widthDraw="1"/>

                    <polygon points="<%=widthDraw / 2 + 25%>,<%=-13 - gableHeight%> <%=widthDraw / 2 + 25%>,<%=-3 - gableHeight%> 15,-5 15,-15" 
                             fill="snow" stroke="black"/>
                    <polygon points="<%=widthDraw / 2 + 25%>,<%=-13 - gableHeight%> <%=widthDraw / 2 + 25%>,<%=-3 - gableHeight%> <%= widthDraw + 35%>,<%= -5%> <%= widthDraw + 35%>,<%= -15%>" 
                             fill="snow" stroke="black"/>

                    <%-- streg for højde med tag --%>
                    <line x1="<%= widthDraw + 50%>" y1="<%= -20 - gableHeight%>" x2="<%= widthDraw + 50%>" y2="<%= heightDraw%>" stroke="black" />
                    <text x="<%=widthDraw + 60%>" y="<%=heightDraw / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde med tag: <%=heightDraw + (int) gableHeight%> </text>

                    <%-- streg for højde uden tag --%>
                    <line x1="<%= widthDraw + 80%>" y1="-15" x2="<%= widthDraw + 80%>" y2="<%= heightDraw%>" stroke="black" />
                    <text x="<%=widthDraw + 90%>" y="<%=heightDraw / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde uden tag: <%=heightDraw%> </text>

                    <%-- streg langs jorden --%>
                    <line x1="0" y1="<%= heightDraw%>" x2="<%= widthDraw + 150%>" y2="<%= heightDraw%>" stroke="black"/>

                    </svg>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
