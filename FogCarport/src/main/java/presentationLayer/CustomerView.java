package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerView extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int customerID = customer.getID();
        List<Order> orders = StorageFacade.getCustomerOrders(customerID);
        request.setAttribute("orders", orders);

        return "customerview";
    }

}
