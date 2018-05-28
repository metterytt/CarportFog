package presentationLayer;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetOrdered extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        BOM carportBOM = (BOM) request.getSession().getAttribute("carportbom");

        List<LineItem> listToBeSaved = carportBOM.getListOfProducts();

        int orderID = carportBOM.getOrderID();
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        int empID = emp.getUserID();
        StorageFacade.addBomToOrder(listToBeSaved, orderID, empID);

        request.setAttribute("complete", "Ordre " + orderID + " er nu sat til ordre, og kunden kan nu betale.");
        HttpSession session = request.getSession();
        session.removeAttribute("carportbom");
        session.removeAttribute("customer");
        session.removeAttribute("totalprice");

        return "employee";
    }

}
