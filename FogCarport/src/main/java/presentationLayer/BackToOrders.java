package presentationLayer;

import functionLayer.CarportException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Removes the session attibutes used when viewing a request in viewbom.jsp.
 *
 * @author Snøvsen
 */
public class BackToOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();
        session.removeAttribute("carportbom");
        session.removeAttribute("customer");
        session.removeAttribute("totalprice");
        return new AllRequests().execute(request, response);
    }

}
