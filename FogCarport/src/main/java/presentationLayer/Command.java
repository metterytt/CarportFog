package presentationLayer;

import functionLayer.CarportException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used to guide the FrontController to the correct servlet.
 *
 * @author Snøvsen
 */
abstract class Command {

    private static HashMap<String, Command> commands;

    /**
     * initialization of all commands. commands consists of a hashmap with a key
     * String, that comes from a hidden field, in the different html forms.
     */
    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("navbar", new Navbar());
        commands.put("inputcarport", new InputCarport());
        commands.put("sendrequest", new SendRequest());
        commands.put("registeremployee", new RegisterEmployee());
        commands.put("registercustomer", new RegisterCustomer());
        commands.put("deleteemployee", new DeleteEmployee());
        commands.put("allcalculations", new AllCalculations());
        commands.put("allrequests", new AllRequests());
        commands.put("setordered", new SetOrdered());
        commands.put("viewbom", new ViewBom());
        commands.put("customerview", new CustomerView());
        commands.put("editrequest", new EditRequest());
        commands.put("edittotalprice", new EditTotalPrice());
        commands.put("viewfinalbom", new ViewFinalBom());
        commands.put("updateQuantityBom", new UpdateQuantityBom());
        commands.put("backtoorders", new BackToOrders());
        commands.put("payfororder", new PayForOrder());
        commands.put("logreader", new LogReader());
    }

    /**
     * This method is used in the FrontController. Retrieves the correct
     * servlet, depending on the commandName, if the servlet exists. Otherwise
     * it throws an exeption in the UnknownCommand servlet.
     *
     * @param request comes from the FrontController.
     * @return the Command/servlet matching the commandName.
     */
    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    /**
     * Method filled out in each servlet, and called in the FrontController. The
     * method determines what will happen on the next jsp-page, and what to
     * fetch from the database.
     *
     * @param request Comes from the FrontController.
     * @param response Comes from the FrontController.
     * @return a String to which jsp page the FronController should forward to.
     * @throws CarportException if something goes wrong when executing the
     * different statements in the servlet.
     */
    abstract String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException;

}
