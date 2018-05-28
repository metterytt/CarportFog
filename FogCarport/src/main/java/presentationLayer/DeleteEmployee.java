package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Employee;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEmployee extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("administrer") == null) {
            int empIdForDeletion = Integer.parseInt(request.getParameter("empIdForDeletion"));
            if (empIdForDeletion != 0) {
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
