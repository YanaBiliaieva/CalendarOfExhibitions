package exhibitions.exception;

/**
 * Halls Exception.
 */
public class HallsException extends Throwable {
    /**
     * Default exception constructor.
     */
    public HallsException() {
    }

    /**
     * Exception with message constructor.
     *
     * @param message Message to pass to handler
     */
    public HallsException(String message) {
        super(message);
    }

}
