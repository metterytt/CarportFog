/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmus
 */
public class CustomerView extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
        Customer customer = (Customer)request.getSession().getAttribute("customer");
        int customerID = customer.getID();
        List<Order> orders = StorageFacade.getCustomerOrders(customerID);
        request.setAttribute("orders", orders);
        
        return "customerview";
    }
    
}
