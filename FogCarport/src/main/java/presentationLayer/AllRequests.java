package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllRequests extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        List<Order> openRequests = StorageFacade.getOpenRequests();
        request.setAttribute("openrequests", openRequests);

        List<Order> orders = StorageFacade.getOrders();
        request.setAttribute("orders", orders);

        return "allrequests";
    }

}
