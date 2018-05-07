/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.Calculators.CarportCalculator;
import functionLayer.Calculators.FlatRoofCalculator;
import functionLayer.BOM;
import functionLayer.Calculators.PitchedRoofCalculator;
import functionLayer.Calculators.ShedCalculator;
import functionLayer.CarportException;
import functionLayer.DrawingMeasures;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mette
 */
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

//        CarportCalculator carportCalculator;
//        
//        if (angle == 0) {
//            carportCalculator = new FlatRoofCalculator(length, width);
//        }
//        else {
//            carportCalculator = new PitchedRoofCalculator(length, width, angle);
//        }
//        BOM carportBom = carportCalculator.getBom();
//        session.setAttribute("carportbom", carportBom);
//
//        if (shedWidth != 0 && shedLength != 0) {
//            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
//            BOM shedBom = shedCalculator.getBom();
//            session.setAttribute("shedbom", shedBom);
//        }
//        
//        //calc rafterGap, rafterQuantity for JSP page
//        for (LineItem l : carportBom.getListOfProducts()) {
//            if (l.getUseInContext().equals("Spær, monteres på rem")||l.getUseInContext().equals("Skal samles")) {
//                int rafterQuantity = l.getQuantity() - 1;
//                double rafterGap = (length - 15) / rafterQuantity;
//
//                request.setAttribute("rafterGap", rafterGap);
//                request.setAttribute("rafterQuantity", ++rafterQuantity);
//            }
//        }
//        request.setAttribute("length", length);
//        request.setAttribute("width", width);
//        request.setAttribute("angle", angle); 
        String shedPos = request.getParameter("shedPos");
        // næste 3 linier er quickfix
        if (shedPos == null) {
            shedPos = "left";
        }
        if ((shedLength == 0 && shedWidth != 0) || (shedLength != 0 && shedWidth == 0)) {
//        if (shedLength == 0 || shedWidth == 0) {
            request.setAttribute("error", "Der skal vælges nummer for Højde og Bredde såfremt du ønsker et skur!");
            return "index";
        } else {
//            request.setAttribute("shedlength", shedLength);
//            request.setAttribute("shedwidth", shedWidth); 
            request.setAttribute("shedPos", shedPos);
        }

        return "bom";
    }
}
