
<%@page import="functionLayer.BOM"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <title>Edit Request</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/Include/Navbar.jspf" %>
        <%
            BOM carportBOM = (BOM) session.getAttribute("carportbom");
            int orderID = carportBOM.getOrderID();
            int carlength = carportBOM.getLength();
            int carwidth = carportBOM.getWidth();
            int angle = carportBOM.getAngle();
            int shedLength = carportBOM.getShedLength();
            int shedWidth = carportBOM.getShedWidth();  %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h1 class="display-4"> Edit request for OrderID : <%= orderID%> </h1>


                    <form  action="FrontController" method="post">
                        <input type="hidden" name="command" value="editrequest">
                        <input type="hidden" name="editfinished">
                        <input type="hidden" name="orderID" value="<%=orderID%>">

                        <label for="length">Længde i CM :</label><br>
                        <select class="custom-select" name="length">
                            <option selected="selected"> <%=carlength%> </option>

                            <%
                                int length = 270;
                                for (int i = 1; i <= 18; i++) {
                                    if (length != carlength) {%> 
                            <option> <%=length%> </option> 
                            <%}
                                    length += 30;
                                }%>
                        </select>

                        <label for="length">Bredde i CM:</label><br>
                        <select class="custom-select" name="width">
                            <option selected="selected"> <%=carwidth%> </option>
                            <%
                                int width = 270;
                                for (int i = 1; i <= 17; i++) {
                                    if (carwidth != width) {%> 
                            <option> <%=width%> </option> 
                            <%}
                                    width += 30;
                                }%>
                        </select>

                        <br> <label for="angle">Hvis tagrejsning ønskes, indtast vinkel på tag:</label>
                        <select class="custom-select" name="angle" value="<%=angle%>">
                            <option selected="selected"> <%=angle%> </option>

                            <%
                                int angleForSalesman = 0;
                                for (int i = 1; i <= 10; i++) {
                                    if (i == 1 && angle != 0) {
                            %> <option value="0">Ingen tagrejsning</option>     
                            <%} else if (i == 1 && angle == 0) {
                                ++i;
                            } else if (angleForSalesman != angle) {%>

                            <option value="<%=angleForSalesman%>"> <%=angleForSalesman%>°</option>
                            <%

                                    }
                                    angleForSalesman += 10;
                                    ++i;
                                }%>
                        </select>

                        <br>
                        <br>

                        <%if (shedLength != 0) {%>
                        <label for="shedlength">Længde i CM :</label><br>
                        <select class="custom-select" name="shedlength">
                            <option selected="selected"> <%=shedLength%> </option>
                            <option> 0 </option>

                            <%
                                int shedlengthForSalesman = 210;
                                for (int i = 1; i <= 18; i++) {
                                    if (shedLength != shedlengthForSalesman) {%> 
                            <option> <%=shedlengthForSalesman%> </option> 
                            <%}
                                    shedlengthForSalesman += 30;
                                }%>
                        </select>

                        <label for="shedwidth">Bredde i CM:</label><br>
                        <select class="custom-select" name="shedwidth">
                            <option selected="selected"> <%=shedWidth%> </option>
                            <option> 0 </option>
                            <%
                                int shedwidthForSalesman = 150;
                                for (int i = 1; i <= 19; i++) {
                                    if (shedwidthForSalesman != shedWidth) {%> 
                            <option> <%=shedwidthForSalesman%> </option> 
                            <% }
                                    shedwidthForSalesman += 30;
                                }%>
                        </select>
                        <%}%>
                        <input type="submit" class="btn btn-primary" value="Edit Order!"/><br><br>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
