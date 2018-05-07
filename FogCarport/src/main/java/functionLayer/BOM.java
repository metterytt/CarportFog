package functionLayer;

import functionLayer.entity.LineItem;
import java.util.ArrayList;

public class BOM {

    private ArrayList<LineItem> listOfProducts = new ArrayList<>();
    // BOM får nu målene, så de kan kan hentes ud fra at BOM er i sessionen
    private double length;
    private int width;
    private int angle;
    private int shedLength;
    private int shedWidth;

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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
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
    
}
