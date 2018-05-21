/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PayForOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        StorageFacade.PayForOrder(orderID);
        request.setAttribute("complete", "Betalingen er gennemført, se stykliste for ordre: " + orderID + " Under dine Ordre!");
        
        return new CustomerView().execute(request, response);
    }
    
}
