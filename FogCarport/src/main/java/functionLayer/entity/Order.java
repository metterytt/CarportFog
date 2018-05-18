package functionLayer.entity;

public class Order {
    
    private int orderID;
    private int customer;
    private int length;
    private int width;
    private int angle;
    private int shedLength;
    private int shedWidth;
    private int price;
    private int empID;
    private int placed;

    public Order(int orderID, int customer, int length, int width, int angle, int shedLength, int shedWidth, int price, int empID, int placed) {
        this.orderID = orderID;
        this.customer = customer;
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.price = price;
        this.empID = empID;
        this.placed = placed;
    }

    public Order(int OrderID, int length, int width, int angle, int shedLength, int shedWidth, int price, int placed) {
        this.orderID = OrderID;
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.price = price;
        this.placed = placed;
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int isPlaced() {
        return placed;
    }

    public void setPlaced(int placed) {
        this.placed = placed;
    }

   
    
}
