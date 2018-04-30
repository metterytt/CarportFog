/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import functionLayer.entity.LineItem;
import java.util.ArrayList;

/**
 *
 * @author mette
 */
public class BOM {

    private ArrayList<LineItem> listOfProducts = new ArrayList<>();

    public void addToBOM(LineItem product) {
        listOfProducts.add(product);
    }

    public ArrayList<LineItem> getListOfProducts() {
        return listOfProducts;
    }

}
