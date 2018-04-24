<%-- 
    Document   : bom
    Created on : 24-04-2018, 19:33:17
    Author     : mette
--%>

<%@page import="functionLayer.BOM"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Styklisteberegning</h1>
        <% BOM bom = (BOM) session.getAttribute("bom");
        %>
        
        <table border="1">
            <tr>
                <th><p>Varetype</th>
                <th><p>Antal</th>
            </tr>
            <tr>
                <th><p>Stolper</th>
                <td><p><%out.print(bom.getPosts()); %></td>
            </tr>
            
        </table>
        
        
    </body>
</html>
