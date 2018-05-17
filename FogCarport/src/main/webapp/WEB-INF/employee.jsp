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
        <title>Profile page</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">


                    <% Employee emp = (Employee) request.getSession().getAttribute("employee");%>

                    <h2 class="display-4">Velkommen <%= emp.getUsername()%> til medarbejdersiden.</h2>
                    <br>
                    <% if(request.getAttribute("complete") != null){ %>
                    <div class="p-2 bg-success text-black col-md-7 text-center">${complete}</div><br>
                    <%}%>
                    <% if (emp.getRole().equals("IT")) { %>
                    <% if (request.getAttribute("allEmp") == null) { %>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="deleteemployee">
                        <input type="hidden" name="administrer" value="administrer">
                        <input type="submit" class="btn btn-primary" value="Administrer brugere">
                    </form>
                    <%}

                    else {%>
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
                        <p class="text-danger"> <%=error%>
                            <%}%> </p>

                        <input type="submit" class="btn btn-primary" value="Slet bruger">
                    </form>
                    <%}%>
                    <br><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="registeremployee">
                        <input type="submit" class="btn btn-primary" value="Registrer ny medarbejder">
                    </form>
                    <%}%>
                    <br><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="allcalculations">
                        <input type="submit" class="btn btn-primary" value="Se alle indtastede beregninger">
                    </form>

                    <br><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="allrequests">
                        <input type="hidden" name="showrequests">
                        <input type="submit" class="btn btn-primary" value="Se alle åbne forespørgsler">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
