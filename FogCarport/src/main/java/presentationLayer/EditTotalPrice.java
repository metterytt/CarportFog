package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
