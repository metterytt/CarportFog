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

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    


                    <br><h1>Visualisering</h1>
                    
                    <p> ${message} </p>
                    <% if(request.getAttribute("userDetailsNeeded") != null){ %>
                    <p> ${userDetailsNeeded} </p>

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="number" name="phonenumber">
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel">
                    </form>
                    
                    <form action="FrontController" method="post">
                     <input type="hidden" name="command" value="navbar">
                     <input type="hidden" name="login" value="login">
                     <input type="submit" class="btn btn-primary" value="Login">
                    </form>
                    <%} %>
                    
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
                    </form>


                    
                    <br><h1>Visualisering</h1>

                    <%
                        DrawingMeasures drawingMeasures = (DrawingMeasures) request.getSession().getAttribute("drawingmeasures");
                        int length = drawingMeasures.getLength();
                        int width = drawingMeasures.getWidth();
                        int height = drawingMeasures.getHeight();
                        int angle = drawingMeasures.getAngle();
                        int shedLength = drawingMeasures.getShedLength();
                        int shedWidth = drawingMeasures.getShedWidth();

                        String shedPos = (String) request.getAttribute("shedPos");  
                        double rafterGap = drawingMeasures.getRafterGap();
                        int rafterQty = drawingMeasures.getRafterQty();
                        int posts = drawingMeasures.getPosts();
                        double startingLength = (length - 10);   
                    %>
                  

                    <%-- carport set oppefra --%>
                    <svg height="500" width="500" viewbox="0 0 <%= width + 150%> <%= length + 60%>">

                    <%-- remme --%>
                    <line x1="<%= width - 15%>" y1="0" x2="<%= width - 15%>" y2="<%= length%>" stroke="black" stroke-width="12" stroke-opacity = "0.5"/>
                    <line x1="<%= width - width + 15%>" y1="0" x2="<%= width - width + 15%>" y2="<%= length%>" stroke="black" stroke-width="12" stroke-opacity = "0.5"/>

                    <%-- spær --%>
                    <%
                        for (int idx = 0; idx < rafterQty; idx++) {
                    %> <line x1="5" y1="<%= startingLength%>" x2="<%= width - 5%>" y2="<%= startingLength%>" stroke-width="12" stroke="darkgrey"/> <%
                            startingLength -= rafterGap;
                        }
                    %>

                    <%-- stolper --%>
                    <%  if (posts < 5) {

                    %> <rect x="<%= width - width + 15 - 4%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none" />
                    <rect x="<%= width - width + 15 - 4%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width - 15 - 11%>" y="<%=length * 0.25%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - 15 - 11%>" y="<%=length * 0.75%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <%}
                    else {

                    %> <rect x="<%= width - width + 15 - 4%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width + 15 - 4%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width - 15 - 11%>" y="<%=length * 0.1%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - 15 - 11%>" y="<%=length * 0.9%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

                    <rect x="<%= width - 15 - 11%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>
                    <rect x="<%= width - width + 15 - 4%>" y="<%=length / 2%>" height="15" width="15" stroke="black" stroke-width="3" fill="none"/>

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


                    <%  if(shedLength != 0 && shedPos.equals("middle")){
                        shedWidth = width-22;
                        shedPos = "left";
                    }
                        if (shedLength != 0 && shedWidth != 0 && shedPos.equals("left")) {%>

                    <%-- Skur VENSTRE --%>
                    <rect x="11" y="15" height="<%=shedLength%>" width="<%=shedWidth%>" stroke="red" stroke-width="2" fill="none" stroke-dasharray="10 10"/>
                    <%--  stolper til skur - venstre--%>
                    <rect x="11" y="15" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= shedWidth - 4%>" y="<%= shedLength%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= shedWidth - 4%>" y="15" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="11" y="<%= shedLength%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>


                    <%}
                    else if (shedLength != 0 && shedWidth != 0) {%>

                    <%-- Skur til højre --%>
                    <rect x="<%= width - shedWidth - 11%>" y="15" height="<%=shedLength%>" width="<%=shedWidth%>" stroke="red" stroke-width="2" fill="none" stroke-dasharray="10 10"/>
                    <%--  stolper til skur - venstre--%>
                    <rect x="<%= width - shedWidth - 11%>" y="15" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width - shedWidth - 11%>" y="<%= shedLength%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width - 26%>" y="15" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>
                    <rect x="<%= width - 26%>" y="<%= shedLength%>" height="15" width="15" stroke="red" stroke-width="3" fill="none"/>

                    <%}%>

                    </svg>














                    <%-- carport set fra siden med fladt tag --%>

                    <%-- checker om der er fladt tag --%>
                    <% if (angle == 0) {%>

                    <%-- <svg width="600" height="500" viewbox="-10 -80 <%= width * 2.5%> <%= length%>"> --%>
                    <svg width="1000" height="1000" viewbox="0 -100 <%= width * 3%> <%= length * 2%>"> 


                    <%-- stolper --%>
                    <%
                        if (posts < 5) {
                    %> 

                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <%-- tekst til længde --%>
                    <% if (shedLength == 0) {%>
                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

                    <% if (shedLength != 0) {%>
                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

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

                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length / 2%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <%-- tekst til længde --%>
                    <% if (shedLength == 0) {%>
                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

                    <% if (shedLength != 0) {%>
                    <text x="<%=length / 2%>" y="<%=height - 230%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

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

                    <%-- tekst for højde og hældning --%> 
                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
                    <text x="<%=length + 15%>" y="<%=height / 2 - 25%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text>

                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= length%>" y2="18" stroke="darkgrey" stroke-width="3"/>
                    <line x1="20" y1="5" x2="<%= length%>" y2="13" stroke="black" stroke-width="8"/>

                    <%-- streg for hældning --%>
                    <line x1="20" y1="0" x2="<%= length%>" y2="0" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
                    <line x1="<%= length%>" y1="0" x2="<%= length%>" y2="<%= 15%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>

                    <%-- pattern med striber til skur --%>
                    <defs>
                    <pattern id="patternSkur"
                             width="15" height="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="black" stroke-width="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- checker om der er skur (fladt tag) --%>
                    <% if (shedLength != 0) {%>

                    <% if (length < 600) {%>
                    <polygon points="<%=length - shedLength - 40%>,<%=24%> <%=length - 40%>,<%=29%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% } %>
                    <% if (length >= 600) {%>
                    <polygon points="<%=length - shedLength - 40%>,<%=24%> <%=length - 40%>,<%=26%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% }
                        }%>

                    </svg>

                    <% } %>




















                    <%-- checker om der er hældning på taget --%>
                    <% if (angle > 0) {%>

                    <%-- carport med rejsning set fra siden stor tegning/Nillers tegning --%>

                    <%-- <svg width="600" height="500" viewbox="-10 -80 <%= width * 2.5%> <%= length%>"> --%>
                    <svg width="1000" height="1000" viewbox="0 -100 <%= width * 3%> <%= length * 2%>"> 


                    <%-- stolper --%>
                    <%
                        if (posts < 5) {
                    %> 

                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <% if (shedLength == 0) {%>
                    <text x="<%=length / 2%>" y="<%=height - 160%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

                    <% if (shedLength != 0) {%>
                    <text x="<%=length * 0.3%>" y="<%=height - 160%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>
                    <% } %>

                    <%-- rem til 4 stolper--%>
                    <% if (length < 349) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%--transform="translate(0) rotate(1.7 30 40)"/>--%>
                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%--transform="translate(0) rotate(1.05 0 15)"/> --%>

                    <% }
                        }
                        if (posts > 4) {
                    %>

                    <rect x="50" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length / 2%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= length - 40%>" y="<%=10%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <text x="<%=length * 0.3%>" y="<%=height - 160%>" fill="black" text-anchor="middle">Længde: <%=length%> </text>

                    <%-- rem til 6 stolper--%>
                    <% if (length < 599) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(1 100 50)"/> --%>
                    <% }
                        if (length >= 60) {%>
                    <rect x="20" y="12" width="<%=length - 20%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(0.5 250 50)"/> --%>
                    <% }
                        }%>

                    <%-- stiplet linje til jorden --%>
                    <line x1="0" y1="<%= height + 10%>" x2="<%= length * 1.30%>" y2="<%= height + 10%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>

                    <%-- træ --%>
                    <polygon points="<%= length + 70%>,15 <%= length + 20%>,170 <%= length + 120%>,170" fill="green" stroke="black" stroke-width="1" />
                    <rect x="<%= length + 60%>" y="170" width="20" height="50" fill="saddlebrown" stroke="black"/>

                    <%-- Tekst til højde og hældning --%>
                    <text x="<%=length * 0.05%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde: <%=height%> </text>
                    <%--<text x="<%=length + 15%>" y="<%=height / 2 - 100%>" fill="black" text-anchor="middle" writing-mode="tb">Hældning på 9 cm. </text> --%>

                    <%-- tag og stern --%>
                    <line x1="20" y1="10.5" x2="<%= length%>" y2="10.5" stroke="darkgrey" stroke-width="3"/>
                    <line x1="20" y1="5" x2="<%= length%>" y2="5" stroke="black" stroke-width="8"/>

                    <%-- tag med rejsning --%>
                    <%--streg for hældning--%>
                    <%-- <line x1="20" y1="<%= height - height - 75%>" x2="<%= length%>" y2="<%= height - height - 75%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/>
                    <line x1="<%= length%>" y1="<%= height - height - 75%>" x2="<%= length%>" y2="<%= height - height - 63%>" stroke="black" stroke-width="2" stroke-dasharray="5 5"/> --%>

                    <%-- spær --%>
                    <%
                        if (posts < 5) {
                    %>
                    <%-- spær i toppen til 4 stolper--%>
                    <% if (length < 349) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(1.5 40 10)"/> --%>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(0.7 0 35)"/> --%>
                    <rect x="<%= length - 13%>" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(1 330 135)"/> --%>

                    <% }
                        if (length >= 350) {
                    %>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(1 80 40)"/> --%>

                    <%-- spær i tag-enderne til 4 stolper --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(0.5 0 35)"/> --%>
                    <rect x="<%= length - 11%>" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(1 330 135)"/> --%>

                    <% }
                        }%>

                    <%-- spær i toppen til 6 stolper--%>
                    <%
                        if (posts > 4) {
                    %>

                    <% if (length < 599) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(1 80 40)"/> --%>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(0.5 0 35)"/> --%>
                    <rect x="<%= length - 11%>" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(1 330 135)"/> --%>
                    <% }
                        if (length >= 600) {%>
                    <rect x="30" y="<%=height - height - 70%>" width="<%=length - 42%>" height="10" fill="snow" stroke="black" stroke-width="1" />
                          <%-- transform="translate(0) rotate(1 80 40)"/> --%>

                    <%-- spær i tag-enderne --%>
                    <rect x="20" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(0.5 0 35)"/> --%>
                    <rect x="<%= length - 11%>" y="<%=height - height - 75%>" width="10" height="76" fill="snow" stroke="black" stroke-width="1"/>
                          <%-- transform="translate(0) rotate(1 330 135)"/> --%>
                    <% }
                        }%>

                    <%-- pattern med striber til tag. --%>
                    <defs>
                    <pattern id="pattern"
                             width="15" height="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="darkgrey" stroke-width="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- Lodrette striper på tag --%>
                    <rect x="31" y="<%=height - height - 60%>"
                          width="<%= length - 42%>" height="61"
                          fill= "url(#pattern)"
                          stroke="none"
                          stroke-width="2px" />
                          <%-- transform="translate(0) rotate(1.2 80 45)"/> --%>

                    <%-- pattern med striber til skur --%>
                    <defs>
                    <pattern id="patternSkur"
                             width="15" height="10"
                             patternUnits="userSpaceOnUse"
                             patternTransform="rotate(0 0 0)">
                        <line stroke="black" stroke-width="3px" y2="10"/>
                    </pattern>
                    </defs>

                    <%-- checker om der er skur (fladt tag) --%>
                    <% if (shedLength != 0) {%>

                    <% if (length < 600) {%>
                    <polygon points="<%=length - shedLength - 40%>,<%=22%> <%=length - 40%>,<%=22%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% } %>
                    <% if (length >= 600) {%>
                    <polygon points="<%=length - shedLength - 40%>,<%=22%> <%=length - 40%>,<%=22%> <%= length - 40%>,<%= height + 10%> <%= length - shedLength - 40%>,<%= height + 10%>" 
                             fill="url(#patternSkur)" stroke="black"/>
                    <% }
                        }%>
                    </svg>







                    <%-- carport set forfra med rejsning --%>
                    <%-- <svg width="600" height="500" viewbox="-10 -80 <%= width * 3%> <%= length%>"> --%>
                    <svg width="1000" height="1000" viewbox="-200 -200 <%= width * 3%> <%= length * 2%>"> 

                    <%-- stolper --%>
                    <rect x="30" y="<%=height - height%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= width + 10%>" y="<%=height - height%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <%-- over- og understern --%>
                    <rect x="15" y="-10" width="<%= width + 20%>" height="10" fill="snow" stroke="black"/>

                    <%-- gavl --%>
                    <%
                        double calcAngle = Math.toRadians(angle);
                        double gableHeight = (width / 2) * Math.tan(calcAngle);
                    %>
                    <rect x="<%= width / 2 + 20%>" y="<%= -10 - gableHeight%>" width="10" height="<%= gableHeight%>" fill="snow" stroke="black" stroke-width="1"/>

                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> 15,-5 15,-15" 
                             fill="snow" stroke="black"/>
                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> <%= width + 35%>,<%= -5%> <%= width + 35%>,<%= -15%>" 
                             fill="snow" stroke="black"/>

                    <%-- streg for højde med tag --%>
                    <line x1="<%= width + 50%>" y1="<%= -20 - gableHeight%>" x2="<%= width + 50%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 60%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde med tag: <%=height + (int) gableHeight%> </text>

                    <%-- streg for højde uden tag --%>
                    <line x1="<%= width + 80%>" y1="-15" x2="<%= width + 80%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 90%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde uden tag: <%=height%> </text>

                    <%-- streg langs jorden --%>
                    <line x1="0" y1="<%= height%>" x2="<%= width + 150%>" y2="<%= height%>" stroke="black"/>

                    </svg>
                    <% }%>
                </div>
            </div>
        </div>
    </body>
</html>
