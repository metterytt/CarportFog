package presentationLayer;

import functionLayer.entity.BOM;
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

/**
 * Retrieves the request from the database and calculates a bill of material.
 *
 * @author Snøvsen
 */
public class ViewBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();

        int orderID = Integer.parseInt(request.getParameter("orderID"));

        Order order = StorageFacade.getOrder(orderID);

        CarportCalculator carportCalculator;

        if (order.getAngle() == 0) {
            carportCalculator = new FlatRoofCalculator(order.getLength(), order.getWidth(), order.getShedLength(), order.getShedWidth());
        } else {
            carportCalculator = new PitchedRoofCalculator(order.getLength(), order.getWidth(), order.getAngle(), order.getShedLength(), order.getShedWidth());
        }

        BOM carportBom = carportCalculator.getBom();
        Customer customer = StorageFacade.getCustomer(order.getCustomer());

        carportBom.setOrderID(orderID);
        //Used for viewing/updating the total price
        session.setAttribute("totalprice", order.getPrice());
        session.setAttribute("carportbom", carportBom);
        session.setAttribute("customer", customer);

        return "viewbom";
    }

}
