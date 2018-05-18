/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jesper
 */
public class BackToOrders extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
    HttpSession session = request.getSession();
         session.removeAttribute("carportbom");
         session.removeAttribute("customerID");
         session.removeAttribute("totalprice");
         return new AllRequests().execute(request, response);
    }
    
}
