package exhibitions.exception;

public class DAOException extends Exception {
    /**
     * Default exception constructor.
     */
    public DAOException() {
    }

    /**
     * Exception with message constructor.
     *
     * @param message Message to pass to handler
     */
    public DAOException(String message) {
        super(message);
    }

}
