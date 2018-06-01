package functionLayer;

import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Makes long HTML Strings of tables consisting of different lists.
 *
 * @author Snøvsen
 */
public class RenderTables {

    /**
     * Makes a table String of all requests in the database.
     *
     * @param openRequests not null
     * @return String of HTML.
     */
    public static String getOpenRequestsTable(List<Order> openRequests) {
        if (openRequests == null || openRequests.isEmpty()) {
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
                + "<th>Ændre i ordre</th>");
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

            // her formen
            sb.append("<th>");
            sb.append("<form action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewbom\">");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste/Rediger Ordre\"/>");
            sb.append("</form></th>");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    /**
     * Makes a table String of all orders in the database.
     *
     * @param orders not null
     * @return a String of HTML
     */
    public static String getOrdersTable(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
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
                + "<th>Sælger</th><th></th>"
                + "<th>Status</th><th></th>");
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
            sb.append("<input type=\"hidden\" name=\"command\" value=\"viewfinalbom\">");
            sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"customerID\" value=\"").append(o.getCustomer()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"price\" value=\"").append(o.getPrice()).append("\"/>");
            sb.append("<input type=\"hidden\" name=\"orderPlaced\"/>");
            sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste\"/>");
            sb.append("</form></th>");
            if (o.isPlaced() == 1) {
                sb.append("<td> Ikke betalt </td>");
            } else {
                sb.append("<td> Betalt! </td>");
            }
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    /**
     * Makes a table of products for a given order.
     *
     * @param bom not null. Calcualted list from the calculation class.
     * @return a String of HTML.
     */
    public static String getListOfProducts(List<LineItem> bom) {
        if (bom == null || bom.isEmpty()) {
            return "---Ingenting på styklisten---";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>Produktnavn</th><th>Brug</th>"
                + "<th style=\"text-align:right\">Enhed</th><th>Antal</th>"
                + "<th style=\"text-align:right\">Pris</th><th style=\"text-align:right\">Ialt kr.</th>");
        sb.append("</tr></thead><tbody>\n");
        DecimalFormat formatter = new DecimalFormat("#0.00");
        for (LineItem li : bom) {

            double quantity = li.getQuantity();
            String _quantity = formatter.format(quantity);
            _quantity = _quantity.replace(',', '.');
            quantity = Double.parseDouble(_quantity);

            sb.append("<tr>");
            sb.append("<td>").append(li.getName()).append("</td>");
            sb.append("<td>").append(li.getUseInContext()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(li.getUom()).append("</td>");
            sb.append("<td>");
            sb.append("<form class=\"form-inline\" action=\"FrontController\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"command\" value=\"updateQuantityBom\">");
            sb.append("<div class=\"form-group\">");
            sb.append("<input class=\"form-control col-md-6\" type=\"number\" step=\"any\" name=\"editNumber\" value=\"").append(quantity).append("\" required>");
            sb.append("<input type=\"hidden\" name=\"productID\" value=\"").append(li.getProductID()).append("\">");
            sb.append("&nbsp <input type=\"submit\" class=\"btn btn-primary\" value=\"Opdater Antal\">");
            sb.append("</div>");
            sb.append("</form>");
            sb.append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getPricePerUnit())).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getPricePerUnit() * li.getQuantity())).append("</td>");
            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    /**
     * Makes a table String of all calculations ever made.
     *
     * @param custCalcs all calcualtions from the database. Not null.
     * @return a String of HTML.
     */
    public static String getAllCalculations(List<CustomerCalculation> custCalcs) {
        if (custCalcs == null || custCalcs.isEmpty()) {
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

    /**
     * Makes a table String of all requests the given customer has in the
     * database.
     *
     * @param requests not null.
     * @return a String og HTML.
     */
    public static String getCurrentCustomerRequests(List<Order> requests) {
        if (requests == null || requests.isEmpty()) {
            return "---Ingen forespørgsler i øjeblikket---";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Længde</th><th>Bredde</th>"
                + "<th>Tagvinkel</th><th>"
                + "Skur længde</th><th>Skur bredde</th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order r : requests) {
            if (r.isPlaced() == 0) {
                sb.append("<tr>");
                sb.append("<td>").append(r.getOrderID()).append("</td>");
                sb.append("<td>").append(r.getLength()).append("</td>");
                sb.append("<td>").append(r.getWidth()).append("</td>");
                sb.append("<td>").append(r.getAngle()).append("</td>");
                sb.append("<td>").append(r.getShedLength()).append("</td>");
                sb.append("<td>").append(r.getShedWidth()).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</tbody>");
        sb.append("</table>\n");

        return sb.toString();
    }

    /**
     * Makes a table String of all orders the given customer has in the
     * database.
     *
     * @param orders not null.
     * @return a String of HTML.
     */
    public static String getCurrentCustomerOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return "---Ingen ordrer i øjeblikket---";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>ID</th><th>Længde</th><th>Bredde</th>"
                + "<th>Tagvinkel</th><th>"
                + "Skur længde</th><th>Skur bredde</th><th>Total pris</th>");
        sb.append("</tr></thead><tbody>\n");
        for (Order o : orders) {
            if ((o.isPlaced() == 1) || (o.isPlaced() == 2)) {
                sb.append("<tr>");
                sb.append("<td>").append(o.getOrderID()).append("</td>");
                sb.append("<td>").append(o.getLength()).append("</td>");
                sb.append("<td>").append(o.getWidth()).append("</td>");
                sb.append("<td>").append(o.getAngle()).append("</td>");
                sb.append("<td>").append(o.getShedLength()).append("</td>");
                sb.append("<td>").append(o.getShedWidth()).append("</td>");
                sb.append("<td>DKK ").append(o.getPrice()).append(",-</td>");
                if (o.isPlaced() == 1) {
                    sb.append("<td>");
                    sb.append("<form action=\"FrontController\" method=\"post\">");
                    sb.append("<input type=\"hidden\" name=\"command\" value=\"payfororder\">");
                    sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\">");
                    sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Betal\">");
                    sb.append("</form>");
                    sb.append("</td>");
                }
                if (o.isPlaced() == 2) {
                    sb.append("<th>");
                    sb.append("<form action=\"FrontController\" method=\"post\">");
                    sb.append("<input type=\"hidden\" name=\"command\" value=\"viewfinalbom\">");
                    sb.append("<input type=\"hidden\" name=\"orderID\" value=\"").append(o.getOrderID()).append("\"/>");
                    sb.append("<input type=\"submit\" class=\"btn btn-primary\" value=\"Se stykliste\"/>");
                    sb.append("</form></th>");
                }
                sb.append("</tr>");
            }
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

    /**
     * Makes a table String of the given list.
     *
     * @param finalBom a list of the final Bill of Material from the database.
     * Not null.
     * @return a String of HTML.
     */
    public static String getFinalBom(List<LineItem> finalBom) {
        if (finalBom == null || finalBom.isEmpty()) {
            return "---Ingenting på styklisten---";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"table table-striped\">\n"
                + "<thead><tr><th>Produktnavn</th><th>Brug</th><th style=\"text-align:right\">"
                + "Antal</th><th style=\"text-align:right\">Enhed</th>");
        sb.append("</tr></thead><tbody>\n");
        DecimalFormat formatter = new DecimalFormat("###,##0.00");
        for (LineItem li : finalBom) {
            sb.append("<tr>");
            sb.append("<td>").append(li.getName()).append("</td>");
            sb.append("<td>").append(li.getUseInContext()).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(formatter.format(li.getQuantity())).append("</td>");
            sb.append("<td style=\"text-align:right\">").append(li.getUom()).append("</td>");
            sb.append("</tr>\n");
        }
        sb.append("</tbody>");
        sb.append("</table>\n");
        return sb.toString();
    }

}
