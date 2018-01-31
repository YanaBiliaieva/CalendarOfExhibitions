package exhibitions.exception;

public class OrderException  extends Exception {
    /**
     * Default exception constructor.
     */
    public OrderException() {
    }

    /**
     * Exception with message constructor.
     *
     * @param message Message to pass to handler
     */
    public OrderException(String message) {
        super(message);
    }
}
