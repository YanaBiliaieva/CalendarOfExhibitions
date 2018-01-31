package exhibitions.exception;

public class RegistrationException extends Exception {
    /**
     * Default exception constructor.
     */
    public RegistrationException() {
    }

    /**
     * Exception with message constructor.
     *
     * @param message Message to pass to handler
     */
    public RegistrationException(String message) {
        super(message);
    }
}

