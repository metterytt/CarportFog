package presentationLayer;

import functionLayer.CarportException;
import dbAccess.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Updates the totalprice of a request in the database. Take input in from
 * viewbom.jsp
 *
 * @author Snøvsen
 */
public class EditTotalPrice extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int totalprice = Integer.parseInt(request.getParameter("totalprice"));
        StorageFacade.updateTotalPrice(totalprice, orderID);
        request.getSession().setAttribute("totalprice", totalprice);
        return "viewbom";
    }
}
