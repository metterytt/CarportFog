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

/**
 *
 * @author Rasmus
 */
public class EditTotalPrice extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int totalprice = Integer.parseInt(request.getParameter("totalprice"));
        StorageFacade.updateTotalPrice(totalprice, orderID);
        request.getSession().setAttribute("totalprice", totalprice);
        return "viewbom";
    }
}
