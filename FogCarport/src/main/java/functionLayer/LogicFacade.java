/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import dbAccess.StorageFacade;
import functionLayer.entity.Product;

/**
 *
 * @author pernillelorup
 */
public class LogicFacade {

    public static Product getProduct(int productID) throws CarportException {
        return StorageFacade.getProduct(productID);
    }

}
