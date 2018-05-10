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

        StorageFacade.addCustCalc(length, width, angle, shedLength, shedWidth);

        DrawingMeasures drawingMeasures = new DrawingMeasures(length, width, angle, shedLength, shedWidth);
        session.setAttribute("drawingmeasures", drawingMeasures);
        request.setAttribute("drawingmeasures", drawingMeasures);
        
        String shedPos = request.getParameter("shedPos");
        // næste 3 linier er quickfix
        if (shedPos == null) {
            shedPos = "left";
        }
        if ((shedLength == 0 && shedWidth != 0) || (shedLength != 0 && shedWidth == 0)) {
            request.setAttribute("error", "Der skal vælges nummer for Højde og Bredde såfremt du ønsker et skur!");
            return "index";
        } else {
            request.setAttribute("shedPos", shedPos);
        }

        return "bom";
    }
}
