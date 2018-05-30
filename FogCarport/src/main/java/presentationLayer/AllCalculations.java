package presentationLayer;

import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.CustomerCalculation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Shows all gets all calculations from the database, so it can be displayed on allcalculations.jsp
 * @author Snøvsen
 */
public class AllCalculations extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        List<CustomerCalculation> custCalcs = StorageFacade.getCustCalcs();
        request.setAttribute("custcalcs", custCalcs);
        return "allcalculations";
    }

}
