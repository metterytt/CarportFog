package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Employee;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used to display all employees and potentially delete one. administer button
 * on employee.jsp activates a select-option. If no employee has been selected,
 * and delete user has been selected, an exception is thrown.
 *
 * @author Snøvsen
 */
public class DeleteEmployee extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("administrer") == null) {
            int empIdForDeletion = Integer.parseInt(request.getParameter("empIdForDeletion"));
            int noEmpSelected = 0;
            if (empIdForDeletion != noEmpSelected) {
                StorageFacade.deleteEmployee(empIdForDeletion);
            } else {
                request.setAttribute("error", "Fejl - Vælg en bruger, hvis du ønsker at slette en profil.");
            }
        }
        List<Employee> allEmp = StorageFacade.getAllEmployees();
        request.setAttribute("allEmp", allEmp);
        return "employee";
    }

}
