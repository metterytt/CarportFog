/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer.entity;

/**
 *
 * @author mette
 */
public class LineItem {

    private int productID;
    private String name;
    private String description;
    private String uom;
    private int price;
    private String extraInfo;
    private int quantity;
    private String useInContext;

    public LineItem(int productID, String name, String uom, int price) {
        this.productID = productID;
        this.name = name;
        this.uom = uom;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUom() {
        return uom;
    }

    public double getPrice() { // TODO ordentlig formatering af kroner/øre
        return price / 100;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getUseInContext() {
        return useInContext;
    }

    public void setUseInContext(String useInContext) {
        this.useInContext = useInContext;
    }

}
