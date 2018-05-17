package presentationLayer;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.StorageFacade;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetOrdered extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

        // henter bom'ene i sessionen. De bliver sat på sessionen inde i viewbom, som opfriskes hver gang man
        // trykker på en ny 'se stykliste/detaljer' knap. Således ville deres variable også kunne bruges fra sessionen inde 
        // i viewbom.jsp og EditRequest, hvis man ville slippe for en lang liste af variable.
        BOM carportBOM = (BOM) request.getSession().getAttribute("carportbom");

        // som skal i databasen
        int orderID = carportBOM.getOrderID();
        StorageFacade.addBomToOrder(carportBOM.getListOfProducts(), orderID);

        request.setAttribute("complete", "Ordre " + orderID + " er nu sat til afsendt!");
        return "employee";
    }

}
