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
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'ExceptionLogs')" id="defaultOpen">Vis logs for exceptions</button>
                        <button class="btn btn-primary" class="tablinks" onclick="openTable(event, 'CustomerLoginLogs')">Vis logs for kundelogins</button>
                    </div>

                    <%if (request.getAttribute("error") != null) { %>
                    <br> 
                    <div class="p-2 bg-danger text-black col-md-4 text-center">${error}</div><br>
                    <%}%>

                    <%-- List of exception logs --%>
                    <div id="ExceptionLogs" class="tabcontent">
                        <br>
                        <h1 class="display-4">Liste over exceptions opstået på siden</h1>
                        <% for (String str : exceptionLoggingList) {%> 
                        <p><%=str%></p> 
                        <%}%>
                    </div>

                    <%-- List of customer login logs --%>
                    <div id="CustomerLoginLogs" class="tabcontent">
                        <br>
                        <h1 class="display-4">Liste over kundelogins</h1>
                        <% for (String str : customerLoginLoggingList) {%> 
                        <p><%=str%></p> 
                        <%}%>
                    </div>
                </div>
            </div>
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

        <div class="col-md-6">
            <br>
            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="navbar">
                <input type="hidden" name="employee" value="employee">
                <input type="submit" class="btn btn-primary" value="Tilbage til medarbejderside">
            </form>
        </div>

    </body>
</html>
