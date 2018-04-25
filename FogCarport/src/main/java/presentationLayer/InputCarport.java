/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import Calculators.CarportCalculator;
import Calculators.FlatNoShedCalculator;
import functionLayer.BOM;
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

        length = Integer.parseInt(request.getParameter("length"));
        width = Integer.parseInt(request.getParameter("width"));

        CarportCalculator calculator = new FlatNoShedCalculator(length, width);
        BOM bom = calculator.calculateBOM();
        session.setAttribute("bom", bom);
        return "bom";
//        return "stykliste";
    }

}
