/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbAccess;

import functionLayer.CarportException;
import functionLayer.entity.Product;

/**
 *
 * @author pernillelorup
 */
public class StorageFacade {

    public static Product getProduct(int productID) throws CarportException {
        return Mapper.getProduct(productID);
    }
}
