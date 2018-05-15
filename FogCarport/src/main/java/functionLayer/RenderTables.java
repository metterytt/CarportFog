package functionLayer;

import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.text.DecimalFormat;
import java.util.List;

public class RenderTables {
    
//    private static DecimalFormat totalformatter = new DecimalFormat("###,##0.00");

    public static String getOpenRequestsTable(List<Order> openRequests) {
        if (openRequests == null) {
            return "---Ingen forespørgsler i øjeblikket---";
        }
        StringBuilder sb = new StringBuilder();
        DecimalFormat formatter = new DecimalFormat("###,###.-");
        // selve tabellen starter her
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Kunde</th><th>Længde</th><th>Bredde</th>"
                + "<th style=\"text-align:right\">Tagvinkel</th><th style=\"text-align:right\">"
                + "Skur længde</th><th style=\"text-align:right\">Skur bredde</th>"
                + "<th style=\"text-align:right\">Pris</th>"
                + "<th>Sælger</th><th>Ændre i ordre</th><th></th><th></th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order o : openRequests) {
            sb.append("<tr>");
            sb.append("<td>").append(o.getOrderID()).append("</td>");
            sb.append("<td>").append(o.getCustomer()).append("</td>");
            sb.append("<td>").append(o.getLength()).append("</td>");
            sb.append("<td>").append(o.getWidth()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getAngle()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getShedLength()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getShedWidth()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(o.getPrice())).append("</td>");
            sb.append("<td>").append(o.getEmpID()).append("</td>");
            
            // anden form
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewbom\">");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"customerID\" value=\"").append(o.getCustomer()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(o.getLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(o.getWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(o.getAngle()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(o.getShedLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(o.getShedWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"price\" value=\"").append(o.getPrice()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"empID\" value=\"").append(o.getEmpID()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste/Rediger Ordre\"/>");
            sb.append("</form></th>");
            
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    public static String getOrdersTable(List<Order> orders) {
        if (orders == null) {
            return "---Ingen ordrer i systemet---";
        }
        StringBuilder sb = new StringBuilder();
        DecimalFormat formatter = new DecimalFormat("###,###.-");
        // tabellen starter her
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Kunde</th><th>Længde</th><th>Bredde</th>"
                + "<th style=\"text-align:right\">Tagvinkel</th><th style=\"text-align:right\">"
                + "Skur længde</th><th style=\"text-align:right\">Skur bredde</th>"
                + "<th style=\"text-align:right\">Pris</th>"
                + "<th>Sælger</th><th></th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order o : orders) {
            sb.append("<tr>");
            sb.append("<td>").append(o.getOrderID()).append("</td>");
            sb.append("<td>").append(o.getCustomer()).append("</td>");
            sb.append("<td>").append(o.getLength()).append("</td>");
            sb.append("<td>").append(o.getWidth()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getAngle()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getShedLength()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(o.getShedWidth()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(o.getPrice())).append("</td>");
            sb.append("<td>").append(o.getEmpID()).append("</td>");
            // så formen
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewbom\">");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"customerID\" value=\"").append(o.getCustomer()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(o.getLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(o.getWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(o.getAngle()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(o.getShedLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(o.getShedWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"price\" value=\"").append(o.getPrice()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"empID\" value=\"").append(o.getEmpID()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste\"/>");
            sb.append("</form></th>");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    public static String getListOfProducts(List<LineItem> bom, int orderID, int length, int width, int angle, int shedLength, int shedWidth, boolean isNotShed) {
        if (bom == null) {
            return "---Ingenting på styklisten---";
        }
        StringBuilder sb = new StringBuilder();
//        if(isNotShed){
//        
//            sb.append("\n<form action=\"FrontController\" method=\"post\">\n"
//                    + "<input type=\"hidden\" name=\"command\" value=\"editrequest\">\n"
//                    + "<input type=\"hidden\" name=\"parseInfo\"/>");
//            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(orderID).append("\"/>");
//            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(length).append("\"/>");
//            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(width).append("\"/>");
//            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(angle).append("\"/>");
//            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(shedLength).append("\"/>");
//            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(shedWidth).append("\"/>");
//            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Ændre i bestillingen\"/>");
//            sb.append("</form>");
//            
//            
//            sb.append("<form action=\"FrontController\" method=\"post\">");
//            sb.append("<input type=\"hidden\" name=\"command\" value=\"setordered\">");
//            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(orderID).append("\"/>");
//            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Sæt til bestilt\"/>");
//            sb.append("</form>");
//          
//        }
        
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>Produktnavn</th><th>Brug</th><th style=\"text-align:right\">"
                + "Antal</th><th style=\"text-align:right\">Enhed</th>"
                + "<th style=\"text-align:right\">Pris</th><th style=\"text-align:right\">Ialt kr.</th>");
        sb.append("</tr></thead><tbody>\n");
        DecimalFormat formatter = new DecimalFormat("###,##0.00");
        for (LineItem li : bom) {
            sb.append("<tr>");
            sb.append("<td>").append(li.getName()).append("</td>");
            sb.append("<td>").append(li.getUseInContext()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getQuantity())).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(li.getUom()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getPricePerUnit())).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getPricePerUnit() * li.getQuantity())).append("</td>");
            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }
    
    public static String getAllCalculations(List<CustomerCalculation> custCalcs) {
        if (custCalcs == null) {
            return "---Ingen beregninger registreret---";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Længde</th><th>"
                + "Bredde</th><th>Tagvinkel</th>"
                + "<th>Skur længde</th><th>Skur bredde</th>");
        sb.append("</tr></thead><tbody>\n");
        for (CustomerCalculation cc : custCalcs) {
            sb.append("<tr>");
            sb.append("<td>").append(cc.getCalcID()).append("</td>");
            sb.append("<td>").append(cc.getLength()).append("</td>");
            sb.append("<td>").append(cc.getWidth()).append("</td>");
            sb.append("<td>").append(cc.getAngle()).append("</td>");
            sb.append("<td>").append(cc.getShedLength()).append("</td>");
            sb.append("<td>").append(cc.getShedWidth()).append("</td>");
            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

}
