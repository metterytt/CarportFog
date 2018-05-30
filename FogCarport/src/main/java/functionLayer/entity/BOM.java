package functionLayer.entity;

import functionLayer.entity.LineItem;
import java.util.ArrayList;

/**
 * Bill of material is used in the calculationclasses, to hold the information
 * of the order and the list itself.
 *
 * @author Snøvsen
 */
public class BOM {

    private ArrayList<LineItem> listOfProducts = new ArrayList<>();
    private int length;
    private int width;
    private int angle;
    private int shedLength;
    private int shedWidth;
    private int orderID;

    public void addToBOM(LineItem product) {
        listOfProducts.add(product);
    }

    public ArrayList<LineItem> getListOfProducts() {
        return listOfProducts;
    }

    public int totalPrice() {
        int totalPrice = 0;
        for (LineItem lineItem : listOfProducts) {
            totalPrice += lineItem.getPricePerUnit() * lineItem.getQuantity();
        }
        return totalPrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

}
