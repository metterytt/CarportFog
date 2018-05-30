package presentationLayer;

import functionLayer.entity.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.CarportException;
import functionLayer.DrawingMeasures;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Inserts the request into the database. Also manages which site to be shown,
 * if the user want to login/register or if they want to make a new calculation.
 *
 * @author Snøvsen
 */
public class SendRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        // Removes Drawingmeasures object from session if customer decides to enter new measurements
        if (request.getParameter("drawAgain") != null) {
            request.getSession().removeAttribute("drawingmeasures");
            return "index";
        }

        // This checks if newly registered customer has a Drawingmeasures object in the session
        if (request.getParameter("backToDrawing") != null) {
            return "drawings";
        }

        // Gets measurements from DrawingMeasures object for use in the CarportCalculator
        DrawingMeasures drawingMeasures = (DrawingMeasures) request.getSession().getAttribute("drawingmeasures");
        int length = drawingMeasures.getLength();
        int width = drawingMeasures.getWidth();
        int angle = drawingMeasures.getAngle();
        int shedLength = drawingMeasures.getShedLength();
        int shedWidth = drawingMeasures.getShedWidth();

        // Creates CarportCalculator and gets the BOM from the calculator
        CarportCalculator carportCalculator;
        if (angle == 0) {
            carportCalculator = new FlatRoofCalculator(length, width, shedLength, shedWidth);
        } else {
            carportCalculator = new PitchedRoofCalculator(length, width, angle, shedLength, shedWidth);
        }
        BOM carportBom = carportCalculator.getBom();

        // Gets the remaining parameters and places the request
        int price = carportBom.totalPrice();
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int customerID = customer.getID();
        StorageFacade.addRequest(customerID, length, width, angle, shedLength, shedWidth, price);

        request.setAttribute("message", "Din forespørgsel er nu i systemet, og du vil snart blive kontaktet."
                + " \n Du kan se dine forespørgsler under 'Ordreoversigt' ");
        request.getSession().removeAttribute("drawingmeasures");
        return "customer";
    }

}
