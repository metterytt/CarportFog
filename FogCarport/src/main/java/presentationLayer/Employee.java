/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.CustomerCalculation;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mette
 */
public class Employee extends Command {

    public Employee() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        ArrayList<CustomerCalculation> custCalcs = StorageFacade.getCustCalcs();
        request.setAttribute("custcalcs", custCalcs);
        
        
        return "employee";
    }
    
}
