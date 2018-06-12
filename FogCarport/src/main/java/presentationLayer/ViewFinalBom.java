package presentationLayer;

import functionLayer.CarportException;
import dbAccess.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.LineItem;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieves the final bill of material, which is now in the database.
 *
 * @author Snøvsen
 */
public class ViewFinalBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        Order order = StorageFacade.getOrder(orderID);

        List<LineItem> finalBom = StorageFacade.getFinalBom(orderID);
        Customer customer = StorageFacade.getCustomer(order.getCustomer());

        request.setAttribute("customer", customer);
        request.setAttribute("finalbom", finalBom);
        request.setAttribute("orderID", orderID);
        request.setAttribute("price", order.getPrice());

        return "viewfinalbom";
    }

}
