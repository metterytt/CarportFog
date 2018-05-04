/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.Calculator.CarportCalculator;
import functionLayer.Calculator.FlatRoofCalculator;
import functionLayer.BOM;
import functionLayer.Calculator.PitchedRoofCalculator;
import functionLayer.Calculator.ShedCalculator;
import functionLayer.CarportException;
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
        int shedLength = Integer.parseInt(request.getParameter("shedlength"));
        int shedWidth = Integer.parseInt(request.getParameter("shedwidth"));
        int shedAngle = Integer.parseInt(request.getParameter("angle"));
        
        
        StorageFacade.addCustCalc(length, width, shedAngle, shedLength, shedWidth);
        
        
        CarportCalculator carportCalculator;
        
        if (shedAngle == 0) {
            carportCalculator = new FlatRoofCalculator(length, width);
        }
        else {
            carportCalculator = new PitchedRoofCalculator(length, width, shedAngle);
        }
        BOM carportBom = carportCalculator.getBom();
        session.setAttribute("carportbom", carportBom);

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            session.setAttribute("shedbom", shedBom);
        }
        
        //calc rafterGap, rafterQuantity for JSP page
        for (LineItem l : carportBom.getListOfProducts()) {
            if (l.getUseInContext().equals("Spær, monteres på rem")||l.getUseInContext().equals("Skal samles")) {
                int rafterQuantity = l.getQuantity() - 1;
                double rafterGap = (length - 15) / rafterQuantity;

                request.setAttribute("rafterGap", rafterGap);
                request.setAttribute("rafterQuantity", ++rafterQuantity);
            }
        }
        
        request.setAttribute("length", length);
        request.setAttribute("width", width);
        request.setAttribute("shedlength", shedLength);
        request.setAttribute("shedwidth", shedWidth);  
        request.setAttribute("shedangle", shedAngle); 

        return "bom";
    }
}
