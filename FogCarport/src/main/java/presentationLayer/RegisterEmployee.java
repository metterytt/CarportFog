package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Inserts a new employee to the database.
 * Either sends the user to registeremployee.jsp or inserts the employee.
 * @author Snøvsen
 */
public class RegisterEmployee extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        if (request.getParameter("registeremp") != null) {

            String username = request.getParameter("username");
            String psw1 = request.getParameter("password1");
            String psw2 = request.getParameter("password2");
            String role = request.getParameter("empRole");

            if (!psw1.equals(psw2)) {
                request.setAttribute("error", "Passwords skal være ens!");
            } else if (role == null) {
                request.setAttribute("error", "Der skal vælges hvilken afdeling denne medarbejder tilhøre");
            } else {

                StorageFacade.registerEmp(username, psw2, role);
                request.setAttribute("complete", "Brugeren er nu registreret og kan logge ind!");
            }
        }

        return "registeremployee";
    }

}
