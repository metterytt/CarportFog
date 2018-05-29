package functionLayer.entity;

/**
 * This entity is used to handle all products / lineitems.
 * @author Snøvsen
 */
public class LineItem {

    private int productID;
    private String name;
    private String uom;
    private double pricePerUnit;
    private double quantity;
    private String useInContext;

    public LineItem(int productID, String name, String uom, int pricePerUnit) {
        this.productID = productID;
        this.name = name;
        this.uom = uom;
        this.pricePerUnit = pricePerUnit;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public String getUom() {
        return uom;
    }

    public double getPricePerUnit() {
        return pricePerUnit / 100;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getUseInContext() {
        return useInContext;
    }

    public void setUseInContext(String useInContext) {
        this.useInContext = useInContext;
    }

}
