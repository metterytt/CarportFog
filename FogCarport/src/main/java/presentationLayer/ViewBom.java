package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.Calculators.ShedCalculator;
import functionLayer.CarportException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int angle = Integer.parseInt(request.getParameter("angle"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));

        //Used for viewing/updating the total price
        int totalPrice = Integer.parseInt(request.getParameter("price"));
        session.setAttribute("totalprice", totalPrice);

        CarportCalculator carportCalculator;

        if (angle == 0) {
            carportCalculator = new FlatRoofCalculator(length, width, shedLength, shedWidth);
        } else {
            carportCalculator = new PitchedRoofCalculator(length, width, angle, shedLength, shedWidth);
        }
        BOM carportBom = carportCalculator.getBom();

        carportBom.setOrderID(orderID);
        session.setAttribute("carportbom", carportBom);
        session.setAttribute("customerID", customerID);

        return "viewbom";
    }

}
