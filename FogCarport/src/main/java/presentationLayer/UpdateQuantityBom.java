package presentationLayer;

import functionLayer.BOM;
import functionLayer.CarportException;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuantityBom extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
        HttpSession session = request.getSession();
        BOM carportBOM = (BOM) session.getAttribute("carportbom");
       
        double edit = Double.parseDouble(request.getParameter("editNumber"));
        
        int productid = Integer.parseInt(request.getParameter("productID"));

        for (LineItem li : carportBOM.getListOfProducts()) {
            if(li.getProductID() == productid){
                li.setQuantity(edit);
            }
        }
        
        session.setAttribute("carportbom", carportBOM);
        
        request.setAttribute("complete", "Opdatering lavet til antal!");

        return "viewbom";
    }

}
