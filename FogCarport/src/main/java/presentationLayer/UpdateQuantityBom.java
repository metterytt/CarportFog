package presentationLayer;

import functionLayer.CarportException;
import functionLayer.entity.LineItem;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateQuantityBom extends Command{
 
  

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {

//          List<LineItem> bom = (List) request.getSession().getAttribute("carportbom");
          
          request.setAttribute("EditToBom", "EditToBom");
        
          return new ViewBom().execute(request, response);
    }
    
}
