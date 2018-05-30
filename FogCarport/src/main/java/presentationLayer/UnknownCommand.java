package presentationLayer;

import functionLayer.CarportException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used if a Command/servlet cannot be found in the Command class.
 *
 * @author Snøvsen
 */
public class UnknownCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        String msg = "Unknown command. Contact IT";
        throw new CarportException(msg, "index");
    }

}
