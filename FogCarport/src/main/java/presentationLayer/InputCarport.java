package presentationLayer;

import functionLayer.CarportException;
import functionLayer.DrawingMeasures;
import functionLayer.StorageFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InputCarport extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();

        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int angle = Integer.parseInt(request.getParameter("angle"));
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        
        // adds customercalculation to database
        StorageFacade.addCustCalc(length, width, angle, shedLength, shedWidth);
        
        // sets error messages in case of erroneous input
        if ((shedLength == 0 && shedWidth != 0) || (shedLength != 0 && shedWidth == 0)) {
            request.setAttribute("error", "Der skal vælges mål for både højde og bredde såfremt du ønsker et skur.");
            return "index";
        }else if((width - shedWidth <= 29) || length - shedLength <= 29){
           request.setAttribute("error", "Din carport skal være mindst 30 cm bredere samt mindst 30 cm længere end dit skur.");
            return "index";
        }
        
        // if input ok, measures are used in DrawingMeasures object used for drawings. This is saved in session
        DrawingMeasures drawingMeasures = new DrawingMeasures(length, width, angle, shedLength, shedWidth);
        session.setAttribute("drawingmeasures", drawingMeasures);

        return "drawings";
    }
}
