
package presentationLayer;

import functionLayer.CarportException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterEmployee extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CarportException {
    
    return "registeremployee";
    }
    
}
