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

}
