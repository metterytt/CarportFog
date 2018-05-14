package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.Calculators.ShedCalculator;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
        // disse 4 linier er bare så vi beholder listen på over åbne requests på allrequests.jsp
        List<Order> openRequests = StorageFacade.getOpenRequests();
        request.setAttribute("openrequests", openRequests);
        
        List<Order> orders = StorageFacade.getOrders();
        request.setAttribute("orders", orders);
        
        
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int angle = Integer.parseInt(request.getParameter("angle"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        
        CarportCalculator carportCalculator;
        
        if (angle == 0) {
            carportCalculator = new FlatRoofCalculator(length, width);
        }
        else {
            carportCalculator = new PitchedRoofCalculator(length, width, angle);
        }
        BOM carportBom = carportCalculator.getBom();
        carportBom.setOrderID(Integer.parseInt(request.getParameter("orderID")));
        request.setAttribute("carportbom", carportBom);

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            request.setAttribute("shedbom", shedBom);
        }
        
//        return "allrequests";
        return "viewbom";
    }

    
    
}
