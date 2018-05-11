/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author mette
 */
public class RenderTables {

    public static String getOpenRequestsTable(List<Order> openRequests) {
        if (openRequests == null) {
            return "---Ingen forespørgsler i øjeblikket---";
        }
        StringBuilder sb = new StringBuilder();
        // selve tabellen starter her
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Kunde</th><th>Længde</th><th>Bredde</th>"
                + "<th>Tagvinkel</th><th>Skur længde</th><th>Skur bredde</th><th>Pris</th>"
                + "<th>Sælger</th><th>Ændre i ordre</th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order o : openRequests) {
            sb.append("<tr>");
            sb.append("<th>").append(o.getOrderID()).append("</th>");
            sb.append("<th>").append(o.getCustomer()).append("</th>");
            sb.append("<th>").append(o.getLength()).append("</th>");
            sb.append("<th>").append(o.getWidth()).append("</th>");
            sb.append("<th>").append(o.getAngle()).append("</th>");
            sb.append("<th>").append(o.getShedLength()).append("</th>");
            sb.append("<th>").append(o.getShedWidth()).append("</th>");
            sb.append("<th>").append(o.getPrice()).append("</th>");
            sb.append("<th>").append(o.getEmpID()).append("</th>");
            // så første form
            sb.append("<th>\n<form action=\"FrontController\" method=\"post\">\n"
                    + "<input type=\"hidden\" name=\"command\" value=\"editrequest\">\n"
                    + "<input type=\"hidden\" name=\"parseInfo\"/>");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(o.getLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(o.getWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(o.getAngle()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(o.getShedLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(o.getShedWidth()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Ændre i bestillingen\"/>");
            sb.append("</form></th>");
            // anden form
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewbom\">");
            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(o.getLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(o.getWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(o.getAngle()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(o.getShedLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(o.getShedWidth()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste\"/>");
            sb.append("</form></th>");
            // og tredje form
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"setordered\">");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Sæt til bestilt\"/>");
            sb.append("</form></th>");
            sb.append("</tr>\n");
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
        // selve tabellen starter her
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Kunde</th><th>Længde</th><th>Bredde</th>"
                + "<th>Tagvinkel</th><th>Skur længde</th><th>Skur bredde</th><th>Pris</th>"
                + "<th>Sælger</th><th>Bestilt</th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order o : orders) {
            sb.append("<tr>");
            sb.append("<th>").append(o.getOrderID()).append("</th>");
            sb.append("<th>").append(o.getCustomer()).append("</th>");
            sb.append("<th>").append(o.getLength()).append("</th>");
            sb.append("<th>").append(o.getWidth()).append("</th>");
            sb.append("<th>").append(o.getAngle()).append("</th>");
            sb.append("<th>").append(o.getShedLength()).append("</th>");
            sb.append("<th>").append(o.getShedWidth()).append("</th>");
            sb.append("<th>").append(o.getPrice()).append("</th>");
            sb.append("<th>").append(o.getEmpID()).append("</th>");
            sb.append("<th>").append(o.isPlaced()).append("</th>");

            // så formen
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewbom\">");
            sb.append("<input type=\"hidden\" name=\"length\" value=\"").append(o.getLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"width\" value=\"").append(o.getWidth()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"angle\" value=\"").append(o.getAngle()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedlength\" value=\"").append(o.getShedLength()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"shedwidth\" value=\"").append(o.getShedWidth()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste\"/>");
            sb.append("</form></th>");

            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    public static String getListOfProducts(List<LineItem> bom) {
        if (bom == null) {
            return "---Ingenting på styklisten---";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>Produktnavn</th><th>Brug</th><th>Enhed</th><th>Antal</th>"
                + "<th>Pris</th><th>Ialt kr.</th>");
        sb.append("</tr></thead><tbody>\n");
        DecimalFormat formatter = new DecimalFormat("###,##0.00");
        for (LineItem li : bom) {
            sb.append("<tr>");
            sb.append("<th>").append(li.getName()).append("</th>");
            sb.append("<th>").append(li.getUseInContext()).append("</th>");
            sb.append("<th>").append(li.getUom()).append("</th>");
            sb.append("<th>").append(formatter.format(li.getQuantity())).append("</th>");
            sb.append("<th>").append(formatter.format(li.getPricePerUnit())).append("</th>");
            sb.append("<th>").append(formatter.format(li.getPricePerUnit() * li.getQuantity())).append("</th>");
            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

}
