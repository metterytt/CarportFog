<%@page import="functionLayer.RenderDrawings"%>
<%@page import="functionLayer.DrawingMeasures"%>
<%@page import="java.util.ArrayList"%>
<%@page import="functionLayer.entity.LineItem"%>
<%@page import="functionLayer.BOM"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% DrawingMeasures drawingMeasures = (DrawingMeasures) request.getSession().getAttribute("drawingmeasures");
   %>
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
                <div class="col-md-6">


                    <br><h1>Visualisering</h1>

                    <p> ${message} </p>
                    <% if (request.getAttribute("userDetailsNeeded") != null) { %>
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


                    <%

                        int length = drawingMeasures.getLength();
                        int width = drawingMeasures.getWidth();
                        int height = drawingMeasures.getHeight();
                        int angle = drawingMeasures.getAngle();
                        int shedLength = drawingMeasures.getShedLength();

                        String shedPos = (String) request.getAttribute("shedPos");
                        int posts = drawingMeasures.getPosts();
                    %>


                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="hidden" name="shedPos" value="<%= shedPos%>">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
                    </form>

                    <%= RenderDrawings.drawFromAbove(drawingMeasures)%>

                   <%--  <%= RenderDrawings.drawFromSide(drawingMeasures)%> --%>

                    <%= RenderDrawings.drawFromFront(drawingMeasures)%>

                    <%-- 
                                        <%  if (shedLength != 0 && shedPos.equals("middle")) {
                                                shedWidth = width - 22;
                                                shedPos = "left";
                                            }
                                            if (shedLength != 0 && shedWidth != 0 && shedPos.equals("left")) {%>

                    --%>






                    <%-- carport set fra siden med fladt tag --%>

                    <%-- checker om der er fladt tag --%>
                    















                    
                    
                    
                    
                    <%-- carport set forfra med rejsning --%>
                    <svg width="1000" height="1000" viewbox="-200 -200 <%= width * 3%> <%= length * 2%>"> 

                    <%-- stolper --%>
                    <rect x="30" y="0" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>
                    <rect x="<%= width + 10%>" y="<%=height - height%>" width="10" height="<%= height%>" fill="snow" stroke="black" stroke-width="1"/>

                    <%-- over- og understern --%>
                    <rect x="15" y="-10" width="<%= width + 20%>" height="10" fill="snow" stroke="black"/>

                    <%-- gavl --%>
                    <%
                        double calcAngle = Math.toRadians(angle);
                        double gableHeight = (width / 2) * Math.tan(calcAngle);
                    %>
                    <rect x="<%= width / 2 + 20%>" y="<%= -10 - gableHeight%>" width="10" height="<%= gableHeight%>" fill="snow" stroke="black" stroke-width="1"/>

                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> 15,-5 15,-15" fill="snow" stroke="black"/>
                    <polygon points="<%=width / 2 + 25%>,<%=-13 - gableHeight%> <%=width / 2 + 25%>,<%=-3 - gableHeight%> <%= width + 35%>,-5 <%= width + 35%>,-15" fill="snow" stroke="black"/>

                    <%-- streg for højde med tag --%>
                    <line x1="<%= width + 50%>" y1="<%= -20 - gableHeight%>" x2="<%= width + 50%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 60%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde med tag: <%=height + (int) gableHeight%> </text>

                    <%-- streg for højde uden tag --%>
                    <line x1="<%= width + 80%>" y1="-15" x2="<%= width + 80%>" y2="<%= height%>" stroke="black" />
                    <text x="<%=width + 90%>" y="<%=height / 2%>" fill="black" text-anchor="middle" writing-mode="tb">Højde uden tag: <%=height%> </text>

                    <%-- streg langs jorden --%>
                    <line x1="0" y1="<%= height%>" x2="<%= width + 150%>" y2="<%= height%>" stroke="black"/>

                    </svg>
<<<<<<< HEAD
              <%--      <% }%>  --%>
=======




                    
                    <% }%>
>>>>>>> ffa1b5021e289d43f243b2c69872ed1a8b8acfee
                </div>
            </div>
        </div>
    </body>
</html>
