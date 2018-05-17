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
        
        // check lige op på om det er godt med session
        request.getSession().setAttribute("finalbom", finalBom);
        return "viewfinalbom";
    }

}
