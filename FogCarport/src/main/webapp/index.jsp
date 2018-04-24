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
        <title>Welcome page</title>
        
    </head>
    <body>
        <h1>Velkommen til Carport beregner!</h1>
        
        <p>Her kan du indtaste de ønskede mål på din carport.</p>
        <p>Go ahead!</p>
        <p>Please!</p>
        <form name="login" action="FrontController" method="post">
            <input type="hidden" name="command" value="inputcarport">
            <br>Længde: <input type="text" name="length" placeholder="længde...">
            <br>Bredde: <input type="text" name="width" placeholder="bredde...">
            <br><input type="submit" value="Beregn carport">
        </form>
        
        
        <% String error = (String) request.getAttribute("error");
                        if (error != null) {%>
        <p> Error! </p>
        <p> <%=error%>
            <%}%>
    </body>
</html>
