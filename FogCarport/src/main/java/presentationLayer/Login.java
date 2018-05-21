/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.Employee;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmus
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("showEmpLogin") != null) {
            request.setAttribute("empLogin", "empLogin");
            return "login";

        }
        else if (request.getParameter("showCustomerLogin") != null) {
            return "login";
        }
        else if (request.getParameter("loginEmp") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Employee employee = StorageFacade.login(username, password);
            request.getSession().setAttribute("employee", employee);
            return "employee";
        }
        else if (request.getParameter("loginCustomer") != null) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Customer customer;
            try {
                customer = StorageFacade.loginCustomer(username, password);
            request.getSession().setAttribute("customer", customer);
            } catch (IOException ex) {
                //DO something here?
            }
            if(request.getSession().getAttribute("drawingmeasures") != null){
                return "bom";
            }
            
            return "customer";
        }
        return "index";
    }
}
