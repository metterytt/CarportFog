package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayForOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        StorageFacade.PayForOrder(orderID);
        request.setAttribute("complete", "Betalingen er gennemført, se stykliste for ordre: " + orderID + " Under dine Ordre!");

        return new CustomerView().execute(request, response);
    }

}
