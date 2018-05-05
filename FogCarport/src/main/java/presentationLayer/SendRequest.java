package presentationLayer;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();
        BOM carportBom = (BOM) session.getAttribute("carportbom");
        BOM shedBom = (BOM) session.getAttribute("shedbom");
        int length = carportBom.getLength();
        int width = carportBom.getWidth();
        int angle = carportBom.getAngle();
        int shedLength = 0;
        int shedWidth = 0;
        int shedPrice = 0;
        if (shedBom != null) {
            shedLength = shedBom.getShedLength();
            shedWidth = shedBom.getShedWidth();
            shedPrice = shedBom.totalPrice();
        }
        int price = carportBom.totalPrice() + shedPrice;
        StorageFacade.addRequest(length, width, angle, shedLength, shedWidth, price);

        return "requestplaced"; 
    }

}
