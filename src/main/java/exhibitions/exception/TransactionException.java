package exhibitions.exception;

public class TransactionException extends Throwable {
    /**
     * Default exception constructor.
     */
    public TransactionException() {
    }

    /**
     * Exception with message constructor.
     *
     * @param message Message to pass to handler
     */
    public TransactionException(String message) {
        super(message);
    }
}
