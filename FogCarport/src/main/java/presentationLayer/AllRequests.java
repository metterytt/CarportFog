package presentationLayer;

import functionLayer.CarportException;
import dbAccess.StorageFacade;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gets all orders/requests from the database to be displayed on
 * ordermanagement.jsp.
 *
 * @author Snøvsen
 */
public class AllRequests extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        List<Order> openRequests = StorageFacade.getOpenRequests();
        request.setAttribute("openrequests", openRequests);

        List<Order> orders = StorageFacade.getOrders();
        request.setAttribute("orders", orders);

        return "ordermanagement";
    }

}
