/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.Calculator.CarportCalculator;
import functionLayer.Calculator.FlatRoofCalculator;
import functionLayer.BOM;
import functionLayer.Calculator.ShedCalculator;
import functionLayer.CarportException;
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
        int length;
        int width;
        int shedLength;
        int shedWidth;

        length = Integer.parseInt(request.getParameter("length"));
        width = Integer.parseInt(request.getParameter("width"));
        shedLength = Integer.parseInt(request.getParameter("shedlength"));
        shedWidth = Integer.parseInt(request.getParameter("shedwidth"));

        CarportCalculator carportCalculator = new FlatRoofCalculator(length, width);

        BOM carportBom = carportCalculator.getBom();
        session.setAttribute("carportbom", carportBom);

        if (shedWidth != 0 && shedLength != 0) {
            CarportCalculator shedCalculator = new ShedCalculator(shedLength, shedWidth);
            BOM shedBom = shedCalculator.getBom();
            session.setAttribute("shedbom", shedBom);
        }
        return "bom";
    }
}
