<%@page import="functionLayer.entity.Customer"%>
<%@page import="functionLayer.RenderTables"%>
<%@page import="functionLayer.entity.LineItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<LineItem> finalBom = (List<LineItem>) request.getAttribute("finalbom");%>
<% int price = (int) request.getAttribute("price"); %>
<% int orderID = (int) request.getAttribute("orderID"); %>
<%//Used in the include file
    Customer customer = (Customer) request.getAttribute("customer");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Final BOM</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="display-4">Stykliste for ordre: <%= orderID%></h3>
                    <br><h3>Totalpris: <%= price%></h3><br>
                    <table>
                        <thead>
                            <tr>

                                <% if (request.getSession().getAttribute("employee") != null) {%>
                                <%-- Include file for customer information --%>
                                <th> <%@ include file="/WEB-INF/jspf/UserInfo.jspf" %>  </th>
                                <th>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="allrequests">
                                        <input type="submit" class="btn btn-primary" value="Tilbage til oversigt">
                                    </form>   
                                </th>
                                <%} else {%>
                                <th>
                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="customerview">
                                        <input type="submit" class="btn btn-primary" value="Tilbage til oversigt">
                                    </form> 
                                </th>
                                <%}%>
                            </tr>
                        </thead>
                    </table>
                    <br>
                    <%-- Prints the BOM (bill of materials) --%>
                    <%= RenderTables.getFinalBom(finalBom)%>
                </div>
            </div>
        </div>
    </body>
</html>
