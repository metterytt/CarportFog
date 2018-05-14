package presentationLayer;

import functionLayer.BOM;
import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.Calculators.ShedCalculator;
import functionLayer.CarportException;
import functionLayer.DrawingMeasures;
import functionLayer.StorageFacade;
import functionLayer.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        
        if(request.getSession().getAttribute("customer") == null && request.getParameter("phonenumber") == null){
            request.setAttribute("userDetailsNeeded", "Vi har brug for at identificere dig, enten log venglist ind, eller indtast et telefon nummer vi kan kontakte dig på vedrørende din carport.");
            String shedPos = request.getParameter("shedPos");
            request.setAttribute("shedPos", shedPos);
            return "bom";
        }else if(request.getParameter("phonenumber") != null){
            String phonenumber = request.getParameter("phonenumber");
            if(phonenumber.length() != 8){
         request.setAttribute("userDetailsNeeded", "Vi har brug for at identificere dig, enten log venglist ind, eller indtast et telefon nummer vi kan kontakte dig på vedrørende din carport.");
         request.setAttribute("message", "Der skal være 8 cifre i dit tlf-nummer, eksempelvis: 22464462");
         String shedPos = request.getParameter("shedPos");
            request.setAttribute("shedPos", shedPos);
                return "bom"; 
            }
        }

        DrawingMeasures drawingMeasures = (DrawingMeasures) request.getSession().getAttribute("drawingmeasures");

        int length = drawingMeasures.getLength();
        int width = drawingMeasures.getWidth();
        int angle = drawingMeasures.getAngle();
        int shedLength = drawingMeasures.getShedLength();
        int shedWidth = drawingMeasures.getShedWidth();

        CarportCalculator carportCalculator;
        BOM carportBom;
        BOM shedBom = null;

        if (angle == 0) {
            carportCalculator = new FlatRoofCalculator(length, width);
        } else {
            carportCalculator = new PitchedRoofCalculator(length, width, angle);
        }
        carportBom = carportCalculator.getBom();

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            shedBom = shedCalculator.getBom();
        }
        int shedPrice = 0;
        if (shedBom != null) {
            shedPrice = shedBom.totalPrice();
        }
        int price = carportBom.totalPrice() + shedPrice;
//<<<<<<< HEAD
        
        if(request.getParameter("phonenumber") == null){
            Customer customer = (Customer) request.getSession().getAttribute("customer");
            int customerID = customer.getID();
        StorageFacade.addRequest(customerID, length, width, angle, shedLength, shedWidth, price);
        }else{
            int phoneNumber = Integer.parseInt(request.getParameter("phonenumber"));
         StorageFacade.addRequest(phoneNumber, length, width, angle, shedLength, shedWidth, price);
        }
        
        request.setAttribute("message", "Din forespørgsel er nu i systemet, og du vil snart blive kontaktet. \n Du kan se dine forespørgsel under 'Mine forespørgsler' "); 
        
    return "customer";
//=======
//        StorageFacade.addRequest(length, width, angle, shedLength, shedWidth, price);
//        request.setAttribute("message", "Din forespørgsel er nu i systemet, og du vil snart blive kontaktet."); 
//        session.removeAttribute("drawingmeasures");
//        return "index";
//>>>>>>> bomunits
    }

}
