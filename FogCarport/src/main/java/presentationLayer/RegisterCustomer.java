package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Inserts a new customer into the database.
 * Either sends the user to registercustomer.jsp or inserts the user, and then goes to customer.jsp.
 * @author Snøvsen
 */
public class RegisterCustomer extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        // Parameter registercus could be null if sent from login.jsp - the parameter is set when sent from registercustomer.jsp        
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

                // if customer has entered drawing before registering, below attribute is set
                if (request.getSession().getAttribute("drawingmeasures") != null) {
                    request.setAttribute("backtodrawing", "backtodrawing");
                }
                return "customer";
            }
        }
        return "registercustomer";
    }

}
