package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetOrdered extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        StorageFacade.setOrdered(orderID);

        List<Order> openRequests = StorageFacade.getOpenRequests();
        request.setAttribute("openrequests", openRequests);
        
        List<Order> orders = StorageFacade.getOrders();
        request.setAttribute("orders", orders);

        request.setAttribute("complete", "Ordre " + orderID + " er nu sat til afsendt!");
        return "employee";
    }

}
