/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import functionLayer.entity.Product;
import java.util.ArrayList;

/**
 *
 * @author mette
 */
public class BOM {

    private ArrayList<Product> listOfProducts = new ArrayList<>();

    public void addToBOM(Product product) {
        listOfProducts.add(product);
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

}
