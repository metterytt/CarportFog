<%-- 
    Document   : index
    Created on : 23-04-2018, 19:49:32
    Author     : mette
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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1>Velkommen til Carport beregner!</h1>
                    <p>Her kan du indtaste de ønskede mål på din carport.</p>
                    <div class="form-group">
                        <form name="login" action="FrontController" method="post">
                            <input type="hidden" name="command" value="inputcarport">
                            <br> <label for="length">Længde i CM:</label>
                            <input type="text" class="form-control" name="length" min="1" max="5000" placeholder="længde...">
                            <br> <label for="width">Bredde i CM:</label>
                            <input type="text" class="form-control" name="width" min="1" max="5000" placeholder="bredde...">

                            <br>
                            <input type="button" class="btn btn-primary" onclick="myFunction()" value="Vis/Gem skur!"/>
                        
                        <div id="myDIV" style="display:none;">
                               
                            <br> <label for="shedlength">Skur længde i CM:</label>
                            <input type="text" class="form-control" name="shedlength" min="1" max="5000" placeholder="skurlængde...">
                            <br> <label for="shedwidth">Skur bredde i CM:</label>
                            <input type="text" class="form-control" name="shedwidth" min="1" max="5000" placeholder="skurbredde...">
                            </div>
                        

           <input type="submit" class="btn btn-primary" value="Beregn carport">
                            
                        </form>
                    </div>




                    <% String error = (String) request.getAttribute("error"); // Jesper har kommentar til dette
                        if (error != null) {%>
                    <p> Error! </p>
                    <p> <%=error%>
                        <%}%> </p>
                </div>
            </div>
        </div>

    </body>
</html>
