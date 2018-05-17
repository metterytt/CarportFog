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
        //ny 1
        HttpSession session = request.getSession();

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
        } else {
            carportCalculator = new PitchedRoofCalculator(length, width, angle);
        }
        BOM carportBom = carportCalculator.getBom();
        carportBom.setOrderID(Integer.parseInt(request.getParameter("orderID")));
        carportBom.setAngle(angle);
        carportBom.setLength(length);
        carportBom.setWidth(width);
        //ny 2
        carportBom.setOrderID(orderID);
        session.setAttribute("carportbom", carportBom);
        session.removeAttribute("shedbom"); // vi vil ikke have den gamle shedbom liggende hvis vi skal have en ny beregning som ikke har skur
//        request.setAttribute("carportbom", carportBom);

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            //ny 2
            shedBom.setOrderID(orderID);
            shedBom.setShedLength(shedLength);
            shedBom.setShedWidth(shedWidth);
            session.setAttribute("shedbom", shedBom);
//            request.setAttribute("shedbom", shedBom);
        }

        request.setAttribute("orderID", orderID);
        request.setAttribute("customerID", customerID);
        request.setAttribute("length", length);
        request.setAttribute("width", width);
        request.setAttribute("angle", angle);
        request.setAttribute("shedLength", shedLength);
        request.setAttribute("shedWidth", shedWidth);

        String orderPlaced = request.getParameter("orderPlaced");
        if (orderPlaced != null) {
            request.setAttribute("orderPlaced", orderPlaced);
        }

        return "viewbom";
    }

}
