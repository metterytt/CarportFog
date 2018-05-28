package functionLayer;

public class CarportException extends Exception {

    private String page;
    
    public CarportException(String message, String page) {
        super(message);
        this.page = page;
    }

    public String getPage() {
        return page;
    }
    
}
