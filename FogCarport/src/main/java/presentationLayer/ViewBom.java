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
        
       
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
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
        
        request.setAttribute("orderID", orderID);
        request.setAttribute("customerID", customerID);
        request.setAttribute("length", length);
        request.setAttribute("width", width);
        request.setAttribute("angle", angle);
        request.setAttribute("shedLength", shedLength);
        request.setAttribute("shedWidth", shedWidth);
        
        String orderPlaced = request.getParameter("orderPlaced");
        if(orderPlaced != null){
            request.setAttribute("orderPlaced", orderPlaced);
        }

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            request.setAttribute("shedbom", shedBom);
        }
        
//        return "allrequests";
        return "viewbom";
    }

    
    
}
