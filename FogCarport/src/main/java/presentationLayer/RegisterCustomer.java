/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesper
 */
public class RegisterCustomer extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("registercus") != null) {

            String psw1 = request.getParameter("password1");
            String psw2 = request.getParameter("password2");

            if (!psw1.equals(psw2)) {
                request.setAttribute("error", "De indtastede kodeord er ikke ens");
            }
            else {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String mobilenumber = request.getParameter("mobilenumber");
                String username = request.getParameter("username");
                Customer customer = new Customer(username, psw2, firstname, lastname, mobilenumber);
                StorageFacade.registerCustomer(customer);
                request.getSession().setAttribute("customer", customer);
                request.setAttribute("complete", "Din profil er nu blevet oprettet");

                if(request.getSession().getAttribute("drawingmeasures") != null){
                request.setAttribute("backtodrawing", "backtodrawing");
                }
                return "customer";
            }
        }

        return "registercustomer";
    }

}
