package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Updates a request in the database.
 * Either sends user to editequest.jsp or updates request 
 * and then send user to ordermanagement.jsp through the AllRequest.jsp.
 * @author Snøvsen
 */
public class EditRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        if (request.getParameter("parseInfo") != null) {
            return "editrequest";
        }
        if (request.getParameter("editfinished") != null) {

            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int length = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int angle = Integer.parseInt(request.getParameter("angle"));
            int shedLength = Integer.parseInt(request.getParameter("shedlength"));
            int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
            CarportCalculator carportCalculator;
            BOM carportBom;

            if (angle == 0) {
                carportCalculator = new FlatRoofCalculator(length, width, shedLength, shedWidth);
            } else {
                carportCalculator = new PitchedRoofCalculator(length, width, angle, shedLength, shedWidth);
            }
            carportBom = carportCalculator.getBom();

            int price = carportBom.totalPrice();

            StorageFacade.editRequest(orderID, length, width, angle, shedLength, shedWidth, price);

            request.setAttribute("complete", "Opdatering til ordre: " + orderID + "'s mål er hermed lavet.");

        }
        HttpSession session = request.getSession();
        session.removeAttribute("carportbom");
        session.removeAttribute("customer");
        session.removeAttribute("totalprice");
        return new AllRequests().execute(request, response);
    }
}
