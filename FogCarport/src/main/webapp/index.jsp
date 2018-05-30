<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Welcome page</title>

        <script>
            function myFunction() {
                var x = document.getElementById("myDIV");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
        </script>

    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row justify-content-md-center">
                <div class="col-md-6">
                    <div class="card text-center">
                        <div class="card-body">
                            <h1 class="display-4">Velkommen til Carport beregner.</h1>

                            <h4>${message}</h4>
                            <br>

                            <p>Her kan du indtaste de ønskede mål på din carport.</p>
                            <div class="form-group">

                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="inputcarport">

                                    <label for="length">Længde i CM :</label><br>
                                    <select class="custom-select col-md-2" name="length">
                                        <%
                                            int lengthInput = 270;
                                            for (int idx = 1; idx <= 18; idx++) {
                                        %> <option> <%=lengthInput%> </option> 
                                        <% lengthInput += 30;
                                            }%>
                                    </select>

                                    <br><br><label for="length">Bredde i CM:</label><br>
                                    <select class="custom-select col-md-2" name="width">
                                        <%
                                            int widthInput = 270;
                                            for (int idx = 1; idx <= 17; idx++) {
                                        %> <option> <%=widthInput%> </option> 
                                        <% widthInput += 30;
                                            }%>
                                    </select>

                                    <br><br> <label for="angle">Hvis tagrejsning ønskes, indtast vinkel på tag:</label><br>
                                    <select class="custom-select col-md-3" name="angle" min="10" max="40" placeholder="tagvinkel..." value="0">
                                        <option value="0">Ingen tagrejsning</option>
                                        <option value="10">10°</option>
                                        <option value="20">20°</option>
                                        <option value="30">30°</option>
                                        <option value="40">40°</option>
                                    </select>

                                    <br>
                                    <br>
                                    <%-- Button to hide or show fields for shedmeasurements --%>
                                    <input type="button" class="btn btn-primary" onclick="myFunction()" value="Vis/gem skur"/>
                                    <br>

                                    <div id="myDIV" style="display:none;">
                                        <br>
                                        <%-- Button to show information about measures for shed --%>
                                        <%@ include file="/WEB-INF/jspf/ShedInfo.jspf" %>
                                        <br>

                                        <br><label for="shedlength">Længde i CM :</label><br>
                                        <select class="custom-select col-md-2" name="shedlength" value="0">
                                            <option> 0 </option>

                                            <%
                                                int shedlengthInput = 210;
                                                for (int idx = 1; idx <= 18; idx++) {
                                            %> <option> <%=shedlengthInput%> </option> 
                                            <% shedlengthInput += 30;
                                                }%>
                                        </select><br><br>

                                        <label for="shedwidth">Bredde i CM:</label><br>
                                        <select class="custom-select col-md-2" name="shedwidth" value="0">
                                            <option> 0 </option>
                                            <%
                                                int shedwidthInput = 150;
                                                for (int idx = 1; idx <= 19; idx++) {
                                            %> <option> <%=shedwidthInput%> </option> 
                                            <% shedwidthInput += 30;
                                                }%>
                                        </select>
                                    </div>
                                    <br> 
                                    <input type="submit" class="btn btn-primary" value="Beregn carport">  
                                </form>

                                <% String error = (String) request.getAttribute("error");
                                    if (error != null) {%>
                                <br><div class="alert alert-danger" role="alert">
                                    <p>Fejl!</p>
                                    <p><%=error%></p>
                                </div>
                                <%}%> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
