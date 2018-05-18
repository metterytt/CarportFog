package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.Calculators.ShedCalculator;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        // CAN MAYBE HAVE ALL INTS HERE, ALWAYS? THINK ABOUT IT 
        if (request.getParameter("parseInfo") != null) {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int angle = Integer.parseInt(request.getParameter("angle"));
            int shedLength = Integer.parseInt(request.getParameter("shedlength"));
            int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));

            request.setAttribute("orderid", orderID);
            request.setAttribute("length", length);
            request.setAttribute("width", width);
            request.setAttribute("angle", angle);
            request.setAttribute("shedlength", shedLength);
            request.setAttribute("shedwidth", shedWidth);
            return "editrequest";
        }

        if (request.getParameter("editfinished") != null) {

            int shedLength = 0;
            int shedWidth = 0;
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int angle = Integer.parseInt(request.getParameter("angle"));
               shedLength = Integer.parseInt(request.getParameter("shedlength"));
               shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
            CarportCalculator carportCalculator;
            BOM carportBom;

            if (angle == 0) {
                carportCalculator = new FlatRoofCalculator(length, width, shedLength, shedWidth);
            } else {
                carportCalculator = new PitchedRoofCalculator(length, width, angle, shedLength, shedWidth);
            }
            carportBom = carportCalculator.getBom();

            int price = carportBom.totalPrice();// + shedPrice;

            StorageFacade.editRequest(orderID, length, width, angle, shedLength, shedWidth, price);

            request.setAttribute("complete", "Opdatering til ordre: " + orderID + "'s mål er hermed lavet.");

        }
         HttpSession session = request.getSession();
         session.removeAttribute("carportbom");
         session.removeAttribute("customerID");
         session.removeAttribute("totalprice");
        return new AllRequests().execute(request, response);
    }
}
