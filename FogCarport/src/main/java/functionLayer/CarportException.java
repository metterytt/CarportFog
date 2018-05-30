package functionLayer;

/**
 * Used to throw exceptions for the users, when something goes wrong on the
 * site.
 *
 * @author Snøvsen
 */
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
