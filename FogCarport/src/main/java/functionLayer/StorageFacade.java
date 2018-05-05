
package functionLayer;

import dbAccess.Mapper;
import functionLayer.entity.CustomerCalculation;
import functionLayer.entity.Employee;
import functionLayer.entity.LineItem;
import java.util.ArrayList;

public class StorageFacade {
    
    public static LineItem getProduct(int productID) throws CarportException{
        return Mapper.getProduct(productID);
    }

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        Mapper.addCustCalc(length, width, angle, shedLength, shedWidth);
    }
    
    public static Employee login(String username, String password) throws CarportException {
        return Mapper.login(username, password);
    }

    public static ArrayList<CustomerCalculation> getCustCalcs() throws CarportException {
        return Mapper.getCustCalcs();
    }

    public static void addRequest(int length, int width, int angle, int shedLength, int shedWidth, int price) throws CarportException {
        Mapper.addRequest(length, width, angle, shedLength, shedWidth, price);
    }
    
}
