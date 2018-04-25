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
                <th><p>Enhed</th>
                <th><p>Antal</th>
            </tr>
            <tr>
                <th><p>Stolper</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getPosts()); %></td>
            </tr>
            <tr>
                <th><p>Understernbrædder</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getSubFasciaBoards()); %></td>
            </tr>
            <tr>
                <th><p>Sternbrædder</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getFasciaBoards()); %></td>
            </tr>
            <tr>
                <th><p>Remme</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getPlates()); %></td>
            </tr>
            <tr>
                <th><p>Spær</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getRafters()); %></td>
            </tr>
            <tr>
                <th><p>Vandbrædder</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getWaterBoards()); %></td>
            </tr>
            <tr>
                <th><p>Tagplader</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getRoof()); %></td>
            </tr>
            <tr>
                <th><p>Skruer til tag</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getRoofScrews()); %></td>
            </tr>
            <tr>
                <th><p>Hulbånd</th>
                <td><p>Centimeter</td>
                <td><p><%out.print(bom.getMetalTape()); %></td>
            </tr>
            <tr>
                <th><p>Universalbeslag højre</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getRafters()); %></td>
            </tr>
            <tr>
                <th><p>Universalbeslag venstre</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getRafters()); %></td>
            </tr>
            <tr>
                <th><p>Små skruer t.beslag/hulbånd</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getSmallScrews()); %></td>
            </tr>
            <tr>
                <th><p>Bolte</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getPosts()); %></td>
            </tr>
            <tr>
                <th><p>Firkantskiver</th>
                <td><p>Stk.</td>
                <td><p><%out.print(bom.getPosts()*2); %></td>
            </tr>
            
        </table>
        
        
    </body>
</html>
