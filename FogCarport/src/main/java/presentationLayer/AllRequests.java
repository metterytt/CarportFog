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
        
        if((request.getParameter("loadsite") != null) || (request.getParameter("showrequests") != null)){
        List<Order> openRequests = StorageFacade.getOpenRequests();
        request.setAttribute("openrequests", openRequests);
        }
        if(request.getParameter("showorders") != null){
        List<Order> orders = StorageFacade.getOrders();
        request.setAttribute("orders", orders);
        }
        return "allrequests";
    }

}
