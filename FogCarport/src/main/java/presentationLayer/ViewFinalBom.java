package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewFinalBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        List<LineItem> finalBom = StorageFacade.getFinalBom(orderID);
        
        int totalprice = StorageFacade.getOrderTotalPrice(orderID);
        request.setAttribute("finalbom", finalBom);
        request.setAttribute("totalprice", totalprice);
        return "viewfinalbom";
    }

}
