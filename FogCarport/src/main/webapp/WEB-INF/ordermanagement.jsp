<%@page import="functionLayer.entity.LineItem"%>
<%@page import="functionLayer.BOM"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="functionLayer.RenderTables"%>
<%@page import="functionLayer.entity.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Order> openRequests = (List<Order>) request.getAttribute("openrequests");%>
<% List<Order> orders = (List<Order>) request.getAttribute("orders");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Order management</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">

                    <div class="tab">
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'Requests')" id="defaultOpen">Vis åbne forespørgsler</button>
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'Orders')">Vis placerede ordrer</button>
                    </div>

                    <%if (request.getAttribute("complete") != null) { %>
                    <br> 
                    <%-- Message which is send to the page, when one or several factors have been completed. --%>
                    <div class="p-2 bg-success text-black col-md-4 text-center">${complete}</div><br>
                    <%}%>

                    <%-- Requests table --%>
                    <div id="Requests" class="tabcontent">
                        <br>
                        <h3>Forespørgsler</h3>
                        <%= RenderTables.getOpenRequestsTable(openRequests)%>
                    </div>

                    <%-- Orders table --%>
                    <div id="Orders" class="tabcontent">
                        <br>
                        <h3>Ordrer</h3>
                        <%= RenderTables.getOrdersTable(orders)%>
                    </div>

                    <%-- Javascript --%>
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
