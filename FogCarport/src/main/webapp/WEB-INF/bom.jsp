<%@page import="functionLayer.RenderDrawings"%>
<%@page import="functionLayer.DrawingMeasures"%>
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
                        <input type="hidden" name="command" value="navbar">
                        <input type="hidden" name="login" value="login">
                        <input type="submit" class="btn btn-primary" value="Login">
                    </form>
                    <%} %>


                    <%
                        String shedPos = (String) request.getAttribute("shedPos");
                    %>


                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <input type="hidden" name="shedPos" value="<%= shedPos%>">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
                    </form>


                    <%= RenderDrawings.drawFromAbove(drawingMeasures)%>

                    <%= RenderDrawings.drawFromSide(drawingMeasures)%> 

                    <%= RenderDrawings.drawFromFront(drawingMeasures)%>

                </div>
            </div>
        </div>








        <%-- 
                            <%  if (shedLength != 0 && shedPos.equals("middle")) {
                                    shedWidth = width - 22;
                                    shedPos = "left";
                                }
                                if (shedLength != 0 && shedWidth != 0 && shedPos.equals("left")) {%>

        --%>

    </body>
</html>
