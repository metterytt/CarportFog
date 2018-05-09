<%-- 
    Document   : employee
    Created on : 04-05-2018, 11:13:39
--%>

<%@page import="java.util.List"%>
<%@page import="functionLayer.entity.Employee"%>
<%@page import="functionLayer.entity.LineItem"%>
<%@page import="functionLayer.BOM"%>
<%@page import="functionLayer.entity.CustomerCalculation"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">

                    <%@include file="../Include/Navbar.jspf" %>

                    <% Employee emp = (Employee) request.getSession().getAttribute("employee");%>

                    <h2>Velkommen <%= emp.getUsername()%> til medarbejdersiden. Her har du følgende muligheder:</h2>

                     <% if(request.getAttribute("complete") != null){
                        %>
                        <p> EDIT WAS MADE </p>
                        <%} %>
<%else--
                        <% if (emp.getRole().equals("IT")) { %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="deleteemployee">
                        <% List<Employee> emps = (List<Employee>) request.getAttribute("allEmp"); %>
                        <label for="allemployees">Vælg bruger der skal slettes:</label>
                        <select class="custom-select" name="empIdForDeletion">
                            <option value="0">Vælg bruger</option>
                            <% for (Employee e : emps) {
                                    if (emp.getUserID() != e.getUserID()) {
                            %><option value="<%=e.getUserID()%>"><%=e.getUsername()%></option><%
                                    }
                                }
                            %> 
                        </select>
                        <% String error = (String) request.getAttribute("error");
                            if (error != null) {%>
                        <p> <%=error%>
                            <%}%> </p>
                        <input type="submit" class="btn btn-primary" value="Slet medarbejderprofil">
                    </form>

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="registeremployee">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Registrer ny medarbejder">
                    </form>
                    <%}%>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="allcalculations">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Se alle indtastede beregninger">
                    </form>

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="allrequests">
                        <br/>
                        <input type="submit" class="btn btn-primary" value="Se alle åbne forespørgsler">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
