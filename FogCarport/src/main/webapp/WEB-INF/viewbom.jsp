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
<% BOM carportBOM = (BOM) request.getAttribute("carportbom"); %>
<% BOM shedBOM = (BOM) request.getAttribute("shedbom");
%>
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
                        List<LineItem> bom = carportBOM.getListOfProducts();
                        int orderID = (int) request.getAttribute("orderID");
                        int customerID = (int) request.getAttribute("customerID");
                        int length = (int) request.getAttribute("length");
                        int width = (int) request.getAttribute("width");
                        int angle = (int) request.getAttribute("angle");
                        int shedLength = (int) request.getAttribute("shedLength");
                        int shedWidth = (int) request.getAttribute("shedWidth");
                        //Used in the include file
                        Customer customer = StorageFacade.getCustomer(customerID);
                    %>
                    <h3>Styklisteberegning for ordre/forespørgsel <%=carportBOM.getOrderID()%></h3>
                    <h3>Mål: længde: <%=carportBOM.getLength()%>, bredde: <%=carportBOM.getWidth()%> og tagvinkel: <%=carportBOM.getAngle()%> grader</h3>

                    <table>
                        <thead>
                            <tr>
                                <th>    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="editrequest">
                                        <input type="hidden" name="parseInfo">
                                        <input type="hidden" name="orderID" value="<%= orderID%>">
                                        <input type="hidden" name="length" value="<%= length%>">
                                        <input type="hidden" name="width" value="<%= width%>">
                                        <input type="hidden" name="angle" value="<%= angle%>">
                                        <input type="hidden" name="shedlength" value="<%= shedLength%>">
                                        <input type="hidden" name="shedwidth" value="<%= shedWidth%>">
                                        <input type="submit" class="btn btn-primary" value="Ændre i bestillingen">
                                    </form>        </th>
                                    <% if (request.getAttribute("orderPlaced") == null) {%>
                                <th> <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="setordered">
                                        <input type="hidden" name="orderID" value="<%=orderID%>">
                                        <input type="submit" class="btn btn-primary" value="Sæt til bestilt">
                                    </form> </th>
                                    <%}%>
                                <th> <%@ include file="/WEB-INF/jspf/UserInfo.jspf" %>  </th>
                            </tr>
                        </thead>
                    </table>

                    <br>
                    <%= RenderTables.getListOfProducts(bom)%>
                    <h3>Den totale pris for carporten er: <%= formatter.format(carportBOM.totalPrice())%></h3>
                    <%
                        if (shedBOM != null) {
                            List<LineItem> shedBom = shedBOM.getListOfProducts();%>
                    <h2>Herunder er styklisten for skuret:</h2>
                    <%= RenderTables.getListOfProducts(shedBom)%>
                    <h3>Den totale pris for skuret er: <%= formatter.format(shedBOM.totalPrice())%></h3>
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
