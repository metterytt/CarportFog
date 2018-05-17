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

        <title>Visualisering</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">

                    <br><h1>Visualisering</h1>

                    <p> ${message} </p>
                    <% if (request.getAttribute("userDetailsNeeded") != null) { %>
                    <p> ${userDetailsNeeded} </p>

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="navbar">
                        <input type="hidden" name="login" value="login">
                        <input type="submit" class="btn btn-primary" value="Login">
                    </form>

                    <br>
                    <%} %>

                    <div class="tab">
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'fromTop')" id="defaultOpen">Vis carporten fra oven</button>
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'fromSide')">Vis carporten fra siden</button>
                        <% if (drawingMeasures.getAngle() != 0) { %>
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'fromFront')">Vis carporten fra fronten</button>
                        <%}%>
                    </div>

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="sendrequest">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel på denne carport">
                    </form>

                    <br>
                    <div id="fromTop" class="tabcontent">
                        <br>
                        <h3>Carport set fra oven</h3>
                        <%= RenderDrawings.drawFromAbove(drawingMeasures)%>
                    </div>

                    <div id="fromSide" class="tabcontent">
                        <br>
                        <h3>Carport set fra siden</h3>
                        <%= RenderDrawings.drawFromSide(drawingMeasures)%>
                    </div>

                    <% if (drawingMeasures.getAngle() != 0) {%>
                    <div id="fromFront" class="tabcontent">
                        <br>
                        <h3>Carport set fra fronten</h3>
                        <%= RenderDrawings.drawFromFront(drawingMeasures)%>
                    </div>
                    <%}%>

                    <script>
                        function openTable(evt, name) {
                            var i, tabcontent, tablinks;
                            tabcontent = document.getElementsByClassName("tabcontent");
                            for (i = 0; i < tabcontent.length; i++) {
                                tabcontent[i].style.display = "none";
                            }
                            tablinks = document.getElementsByClassName("tablinks");
                            for (i = 0; i < tablinks.length; i++) {
                                tablinks[i].className = tablinks[i].className.replace(" active", "");
                            }
                            document.getElementById(name).style.display = "block";
                            evt.currentTarget.className += " active";
                        }
                        document.getElementById("defaultOpen").click();
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
