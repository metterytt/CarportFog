/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Employee;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmus
 */
public class DeleteEmployee extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        int empIdForDeletion = Integer.parseInt(request.getParameter("empIdForDeletion"));
        if (empIdForDeletion != 0) {
            StorageFacade.deleteEmployee(empIdForDeletion);
        }
        else {
            request.setAttribute("error", "Fejl - Vælg en bruger, hvis du ønsker at slette en profil.");
        }
        List<Employee> allEmp = StorageFacade.getAllEmployees();
        request.setAttribute("allEmp", allEmp);
        return "employee";
    }

}
