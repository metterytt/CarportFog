<%-- 
    Document   : viewbom
    Created on : 13-05-2018, 14:22:43
    Author     : mette
--%>

<%@page import="functionLayer.StorageFacade"%>
<%@page import="functionLayer.entity.Customer"%>
<%@page import="dbAccess.Mapper"%>
<%@page import="functionLayer.entity.Order"%>
<%@page import="functionLayer.RenderTables"%>
<%@page import="functionLayer.entity.LineItem"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.BOM"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% DecimalFormat formatter = new DecimalFormat("###,##0.00"); %>
<% BOM carportBOM = (BOM) session.getAttribute("carportbom"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Ordreoversigt</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">

                    <%

                        //int shedLength = (int) request.getAttribute("shedLength");
                        //int shedWidth = (int) request.getAttribute("shedWidth");
                        int customerID = (int) request.getSession().getAttribute("customerID");
                        //Used for viewing/updating the total price
                        int totalPrice = (int) request.getSession().getAttribute("totalprice");
                        //Used in the include file
                        Customer customer = StorageFacade.getCustomer(customerID);
                    %>

                    <h3 class="display-4">Styklisteberegning for ordre/forespørgsel <%=carportBOM.getOrderID()%></h3>
                    <h3>Mål: længde: <%=carportBOM.getLength()%>, bredde: <%=carportBOM.getWidth()%> og tagvinkel: <%=carportBOM.getAngle()%> grader
          <%--          <% if (shedBOM != null) {%>    har også ændret nedenunder: shed-> carport    --%>
                        <h3> - Skur længde: <%=carportBOM.getShedLength()%>, skur bredde: <%=carportBOM.getShedWidth()%></h3>
          <%--          <%}%>       --%>

                        <table>
                            <thead>
                                <tr>
                                    <th>
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="command" value="editrequest">
                                            <input type="hidden" name="parseInfo">
                                            <input type="hidden" name="orderID" value="<%= carportBOM.getOrderID()%>">
                                            <input type="hidden" name="length" value="<%= carportBOM.getLength()%>">
                                            <input type="hidden" name="width" value="<%= carportBOM.getWidth()%>">
                                            <input type="hidden" name="angle" value="<%= carportBOM.getAngle()%>">
                                            <input type="hidden" name="shedlength" value="<%= carportBOM.getShedLength()%>">
                                            <input type="hidden" name="shedwidth" value="<%= carportBOM.getShedWidth()%>">
                                            <input type="submit" class="btn btn-primary" value="Ændre i bestillingen">
                                        </form>   
                                    </th>
                               <%--     <% if (request.getAttribute("orderPlaced") == null) {%>   --%>

                                    <th> 
                                        <form action="FrontController" method="post">
                                            <input type="hidden" name="command" value="setordered">
                                            <input type="hidden" name="orderID" value="<%=carportBOM.getOrderID()%>">
                                            <input type="submit" class="btn btn-primary" value="Sæt til bestilt">
                                        </form> </th>
                                  <%--      <%}%>   --%>
                                    <th> <%@ include file="/WEB-INF/jspf/UserInfo.jspf" %>  </th>
                                </tr>
                            </thead>
                        </table>
                <%if(request.getAttribute("complete") != null){ %>
                    <div class="p-2 bg-success text-black col-md-4 text-center">${complete}</div><br>
                    <%}%>
                    <br>

                    <h2>Carport:</h2>
                    <%= RenderTables.getListOfProducts(carportBOM.getListOfProducts())%>
                    
                    <br><h3>Prisestimat for carport: <%= formatter.format(carportBOM.totalPrice())%></h3>
           <%--         <% if (shedBOM != null) {
                    
                    
                            List<LineItem> shedBom = shedBOM.getListOfProducts();%>
                    <br><h2 class="display-4">Herunder er styklisten for skuret:</h2>
                    <%= RenderTables.getListOfProducts(shedBom)%>

                    <br><h3>Prisestimat for skur: <%= formatter.format(shedBOM.totalPrice())%></h3>
                    <% }%>
           --%>
           
                    <br><br><h3 class="display-4">Total pris: <%= totalPrice%></h3>
       <%--             <% if (request.getAttribute("orderPlaced") == null) {%>    --%>
                    <div class="form-group">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="edittotalprice">
                            <input type="hidden" name="orderID" value="<%=carportBOM.getOrderID()%>">
                            <label for="password1">Rediger totalpris nedenfor:</label>
                            <input class="form-control col-md-2" type="number" name="totalprice" value="<%= totalPrice%>">
                            <br>
                            <input class="btn btn-primary" type="submit" value="Opdater pris">
                        </form>
                    </div>
         <%--           <%}%>  --%>
                    <br><br>

                </div>
            </div>
        </div>
    </body>
</html>
