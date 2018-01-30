package exception;

public class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(DAOException e) {
    }

    public RegistrationException(Throwable cause) {
        super(cause);
    }
}
