<%-- 
    Document   : logging
    Created on : 22-05-2018, 19:17:22
    Author     : Rasmus
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List<String> exceptionLoggingList = (List<String>) request.getAttribute("exceptionLoggingList");%>
<%List<String> customerLoginLoggingList = (List<String>) request.getAttribute("customerLoginLoggingList");%>


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
                <div class="col-md-6">

                    <div class="tab">
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'CustomerLoginExceptionLogs')" id="defaultOpen">Vis logs for fejlede logins</button>
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'CustomerLoginLogs')">Vis logs for kundelogins</button>
                    </div>

                    <%if (request.getAttribute("error") != null) { %>
                    <br> 
                    <div class="p-2 bg-danger text-black col-md-4 text-center">${error}</div><br>
                    <%}%>

                    <div id="CustomerLoginExceptionLogs" class="tabcontent">
                        <br>
                        <h1 class="display-4">Liste over fejlede logins for kunder</h1>
                        <% for (String str : exceptionLoggingList) {%> 
                        <p><%=str%></p> 
                        <%}%>
                    </div>

                    <div id="CustomerLoginLogs" class="tabcontent">
                        <br>
                        <h1 class="display-4">Liste over logins for kunder</h1>
                        <% for (String str : customerLoginLoggingList) {%> 
                        <p><%=str%></p> 
                        <%}%>
                    </div>
                </div>
            </div>
        </div>

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

    </body>
</html>
