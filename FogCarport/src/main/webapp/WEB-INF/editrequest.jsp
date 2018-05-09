
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
        <%
            int orderID = (int) request.getAttribute("orderid");
            int carlength = (int) request.getAttribute("length");
            int carwidth = (int) request.getAttribute("width");
            int angle = (int) request.getAttribute("angle");
            int shedLength = (int) request.getAttribute("shedlength");
            int shedWidth = (int) request.getAttribute("shedwidth");%>
        <h1> Edit request for OrderID : <%= orderID%> </h1>

        
        <form  action="FrontController" method="post">
         <input type="hidden" name="command" value="editrequest">
        
               <input type="hidden" name="editfinished">

         <input type="hidden" name="orderID" value="<%=orderID%>">
         
            <label for="length">Længde i CM :</label><br>
        <select class="custom-select" name="length">
            <option selected="selected"> <%=carlength%> </option>

            <%
                int length = 270;
                for (int idx = 1; idx <= 18; idx++) {
            if(length != carlength){%> 
            <option> <%=length%> </option> 
            <%} length += 30;
                                    }%>
        </select>

        <label for="length">Bredde i CM:</label><br>
        <select class="custom-select" name="width">
            <option selected="selected"> <%=carwidth%> </option>
            <%
                int width = 270;
                for (int idx = 1; idx <= 17; idx++) {
            if(carwidth != width){%> 
            <option> <%=width%> </option> 
            <%}  width += 30;
                                    }%>
        </select>

        <br> <label for="angle">Hvis tagrejsning ønskes, indtast vinkel på tag:</label>
        <select class="custom-select" name="angle" value="<%=angle%>">
            <option selected="selected"> <%=angle%> </option>

            <%
                int angleForSalesman = 0;
                for (int idx = 1; idx <= 10; idx++) {
                    if (idx == 1 && angle != 0) {
            %> <option value="0">Ingen tagrejsning</option>     
            <%} else if(idx == 1 && angle == 0){
                  ++idx;
                }
            else if(angleForSalesman != angle){%>
                                                
            <option value="<%=angleForSalesman%>"> <%=angleForSalesman%>°</option>
            <%
           
                   }
             angleForSalesman += 10; 
            ++idx;
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
                for (int idx = 1; idx <= 18; idx++) {
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
                for (int idx = 1; idx <= 19; idx++) {
                                            if (shedwidthForSalesman != shedWidth) {%> 
            <option> <%=shedwidthForSalesman%> </option> 
            <% }
                    shedwidthForSalesman += 30;
                }%>
        </select>
        <%}%>
        <br>
        <input type="submit" class="btn btn-primary" value="Edit Order!"/><br><br>
        </form>

    </body>
</html>
