<%-- 
    Document   : index
    Created on : 23-04-2018, 19:49:32
--%>

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
        <%@include file="Include/Navbar.jspf" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1>Velkommen til Carport beregner!</h1>
                    <p>Her kan du indtaste de ønskede mål på din carport.</p>
                    <div class="form-group">
                        <form name="login" action="FrontController" method="post">
                            <input type="hidden" name="command" value="inputcarport">

                            <label for="length">Længde i CM :</label><br>
                            <select class="custom-select" name="length">
                                <%
                                    int length = 270;
                                    for (int idx = 1; idx <= 18; idx++) {
                                %> <option> <%=length%> </option> 
                                <% length += 30;
                                        }%>
                            </select>

                            <label for="length">Bredde i CM:</label><br>
                            <select class="custom-select" name="width">
                                <%
                                    int width = 270;
                                    for (int idx = 1; idx <= 17; idx++) {
                                %> <option> <%=width%> </option> 
                                <% width += 30;
                                        }%>
                            </select>

                            <br> <label for="angle">Hvis tagrejsning ønskes, indtast vinkel på tag:</label>
                            <select class="custom-select" name="angle" min="10" max="40" placeholder="tagvinkel..." value="0">
                                <option value="0">Ingen tagrejsning</option>
                                <option value="10">10°</option>
                                <option value="20">20°</option>
                                <option value="30">30°</option>
                                <option value="40">40°</option>
                            </select>

                            <br/>
                            <input type="button" class="btn btn-primary" onclick="myFunction()" value="Vis/Gem skur!"/>

                            <div id="myDIV" style="display:none;">
                            <label for="shedlength">Længde i CM :</label><br>
                            <select class="custom-select" name="shedlength">
                             <option> 0 </option>

                                <% 
                                    int shedlength = 210;
                                    for (int idx = 1; idx <= 18; idx++) {
                                     %> <option> <%=shedlength %> </option> 
                                    <% shedlength += 30;}%>
                            </select>
                            
                            <label for="shedwidth">Bredde i CM:</label><br>
                            <select class="custom-select" name="shedwidth">
                                <option> 0 </option>
                                <% 
                                    int shedwidth = 150;
                                    for (int idx = 1; idx <= 19; idx++) {
                                     %> <option> <%=shedwidth %> </option> 
                                    <% shedwidth += 30;}%>
                            </select>
                            
                            <p> Højre eller venstre siddende skur? </p>
                            <input type="radio" name="shedPos" value="right">Højre
                           <input type="radio" name="shedPos" value="left">Venstre

                            </div>

                            <input type="submit" class="btn btn-primary" value="Beregn carport">
                        </form>

                    </div>

                    <% String error = (String) request.getAttribute("error"); // Jesper har kommentar til dette
                        if (error != null) {%>
                    <p> Error! </p>
                    <p> <%=error%>
                        <%}%> </p>
                    
                    
                    
                    <form name="login" action="FrontController" method="post">
                            <input type="hidden" name="command" value="employee">
                            <br/>
                            <input type="submit" class="btn btn-primary" value="Gå til medarbejderside">
                        </form>
                    
                    
                    
                    
                    
                    
                    
                    
                </div>
            </div>
        </div>

    </body>
</html>
