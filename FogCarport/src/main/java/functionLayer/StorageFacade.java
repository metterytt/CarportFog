
package functionLayer;

import dbAccess.Mapper;
import functionLayer.entity.LineItem;

public class StorageFacade {
    
    public static LineItem getProduct(int productID) throws CarportException{
        return Mapper.getProduct(productID);
    }

    public static void addCustCalc(int length, int width, int angle, int shedLength, int shedWidth) throws CarportException {
        Mapper.addCustCalc(length, width, angle, shedLength, shedWidth);
    }
    
}
