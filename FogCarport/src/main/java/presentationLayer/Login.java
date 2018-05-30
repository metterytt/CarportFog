package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.Employee;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gives the possibillity to login as employee or customer. If logged in as
 * either, the information will get fetched from the database if exists. If a
 * drawing/calculation has been bade before logging in, the user is directed
 * back to finish the request. If something goes wrong an exception is thrown.
 *
 * @author Snøvsen
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("showEmpLogin") != null) {
            request.setAttribute("empLogin", "empLogin");
            return "login";
        } else if (request.getParameter("showCustomerLogin") != null) {
            return "login";
        } else if (request.getParameter("loginEmp") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Employee employee = StorageFacade.login(username, password);
            request.getSession().setAttribute("employee", employee);
            return "employee";
        } else if (request.getParameter("loginCustomer") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Customer customer;
            customer = StorageFacade.loginCustomer(username, password);
            request.getSession().setAttribute("customer", customer);
            if (request.getSession().getAttribute("drawingmeasures") != null) {
                return "drawings";
            }
            return "customer";
        }

        throw new CarportException("Noget gik galt..", "index");
    }
}
