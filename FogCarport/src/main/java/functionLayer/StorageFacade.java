
package functionLayer;

import dbAccess.Mapper;
import functionLayer.entity.LineItem;

public class StorageFacade {
    
    public static LineItem getProduct(int productID) throws CarportException{
        return Mapper.getProduct(productID);
    }
    
}
