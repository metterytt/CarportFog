<%-- 
    Document   : logging
    Created on : 22-05-2018, 19:17:22
    Author     : Rasmus
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Logging</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <% if(request.getAttribute("logginglistCustomer") != null){
                    List<String> list = (List<String>) request.getAttribute("logginglistCustomer");
                    %>
                <div class="col-md-6">
                    <h1 class="display-4">Liste over Logins for Kunder</h1>

                    <% for (String str : list) {%> 
                    <p><%=str%></p> 
                    <%}%>
                    </div>
                    <% }%>
                    
                     <% if(request.getAttribute("logginglist") != null){
                    List<String> list = (List<String>) request.getAttribute("logginglist");
                    %>
                <div class="col-md-6">
                    <h1 class="display-4">Liste over Logins for Kunder</h1>

                    <% for (String str : list) {%> 
                    <p><%=str%></p> 
                    <%}%>
                    </div>
                    <% }%>
                    
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="logreader">
                        <input type="hidden" name="showCustomerLog">
                        <input type="submit" class="btn btn-primary" value="Vis logs for kunderlogin">
                    </form>
                    
                    </form>
                    <br><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="logreader">
                        <input type="hidden" name="showExceptionLogs">
                        <input type="submit" class="btn btn-primary" value="Vis logs for expcetions">
                    </form>
                    
                    
                </div>
            
        </div>
    </body>
</html>
