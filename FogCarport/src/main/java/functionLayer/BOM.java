package functionLayer;

import functionLayer.entity.LineItem;
import java.util.ArrayList;

public class BOM {

    private ArrayList<LineItem> listOfProducts = new ArrayList<>();

    public void addToBOM(LineItem product) {
        listOfProducts.add(product);
    }

    public ArrayList<LineItem> getListOfProducts() {
        return listOfProducts;
    }

    public int totalPrice() { 
        int totalPrice = 0;
        for (LineItem lineItem : listOfProducts) {
            totalPrice += lineItem.getPrice() * lineItem.getQuantity();
        }
        return totalPrice;
    }

}
