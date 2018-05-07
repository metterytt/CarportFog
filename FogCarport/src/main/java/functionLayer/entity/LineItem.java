package functionLayer.entity;

public class LineItem {

    private int productID;
    private String name;
    private String description;
    private String uom;
    private String uomForDisplay;
    private double pricePerUnit;
    private String extraInfo;
    private double quantity;
    private String useInContext;
    private double qtyForDisplay;
    private int length;

    public LineItem(int productID, String name, String uom, int pricePerUnit) {
        this.productID = productID;
        this.name = name;
        this.uom = uom;
        this.pricePerUnit = pricePerUnit;
        uomForDisplay = uom;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        qtyForDisplay = quantity;
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

    public double getPricePerUnit() { // TODO ordentlig formatering af kroner/øre
        return pricePerUnit / 100;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getUomForDisplay() {
        return uomForDisplay;
    }

    public double getQtyForDisplay() {
        return qtyForDisplay;
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

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
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

    public void setUomForDisplay(String uomForDisplay) {
        this.uomForDisplay = uomForDisplay;
    }

    public void setQtyForDisplay(int qtyForDisplay) {
        this.qtyForDisplay = qtyForDisplay;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
