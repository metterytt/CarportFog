package presentationLayer;

import functionLayer.entity.BOM;
import functionLayer.CarportException;
import functionLayer.entity.LineItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Updates the quantity of the given product. Input comes from viewbom..
 *
 * @author Snøvsen
 */
public class UpdateQuantityBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();
        BOM carportBOM = (BOM) session.getAttribute("carportbom");

        double edit = Double.parseDouble(request.getParameter("editNumber"));
        int productid = Integer.parseInt(request.getParameter("productID"));

        String useincontext = request.getParameter("useincontext");
        if (!(productid == 5 || productid == 6)) {
            for (LineItem li : carportBOM.getListOfProducts()) {
                if (li.getProductID() == productid) {
                    li.setQuantity(edit);
                }
            }
        }
        if ((productid == 5 || productid == 6) && !useincontext.contains("Skur")) {
            for (LineItem li : carportBOM.getListOfProducts()) {
                if (li.getProductID() == productid) {
                    li.setQuantity(edit);
                    break;
                }
            }
        }
        if ((productid == 5 || productid == 6) && useincontext.contains("Skur")) {
            for (LineItem li : carportBOM.getListOfProducts()) {
                if (li.getUseInContext().contains("Skur") && li.getProductID() == productid) {
                    li.setQuantity(edit);
                }
            }
        }

        session.setAttribute("carportbom", carportBOM);
        request.setAttribute("complete", "Opdatering lavet til antal!");

        return "viewbom";
    }

}
