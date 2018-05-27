package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import functionLayer.entity.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();
        
        int orderID = Integer.parseInt(request.getParameter("orderID"));
//        int customerID = Integer.parseInt(request.getParameter("customerID"));
//        int length = Integer.parseInt(request.getParameter("length"));
//        int width = Integer.parseInt(request.getParameter("width"));
//        int angle = Integer.parseInt(request.getParameter("angle"));
//        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
//        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        
        Order order = StorageFacade.getOrder(orderID);
        
        //Used for viewing/updating the total price
        session.setAttribute("totalprice", order.getPrice());
//        int totalPrice = Integer.parseInt(request.getParameter("price"));
//        
//        session.setAttribute("totalprice", totalPrice);

        CarportCalculator carportCalculator;

        if (order.getAngle() == 0) {
            carportCalculator = new FlatRoofCalculator(order.getLength(), order.getWidth(), order.getShedLength(), order.getShedWidth());
        } else {
            carportCalculator = new PitchedRoofCalculator(order.getLength(), order.getWidth(), order.getAngle(), order.getShedLength(), order.getShedWidth());
        }
        
//        if (angle == 0) {
//            carportCalculator = new FlatRoofCalculator(length, width, shedLength, shedWidth);
//        } else {
//            carportCalculator = new PitchedRoofCalculator(length, width, angle, shedLength, shedWidth);
//        }
        BOM carportBom = carportCalculator.getBom();
        Customer customer = StorageFacade.getCustomer(order.getCustomer());

//        Customer customer = StorageFacade.getCustomer(customerID);
        
        carportBom.setOrderID(orderID);
        session.setAttribute("carportbom", carportBom);
        session.setAttribute("customer", customer);

        return "viewbom";
    }

}
