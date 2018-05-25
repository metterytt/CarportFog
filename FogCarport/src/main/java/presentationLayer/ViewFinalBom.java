package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewFinalBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
//        return "index";
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int price = Integer.parseInt(request.getParameter("price"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        
        List<LineItem> finalBom = StorageFacade.getFinalBom(orderID);
        Customer customer = StorageFacade.getCustomer(customerID);
        
//       --------------------------- Nedenstående metode skal muligvis slettes -------------------- #################################
//        int totalprice = StorageFacade.getOrderTotalPrice(orderID);

        request.setAttribute("customer", customer);
        request.setAttribute("finalbom", finalBom);
        request.setAttribute("orderID", orderID);
        request.setAttribute("price", price);
        
        return "viewfinalbom";
    }

}
