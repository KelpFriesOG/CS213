public class AppointmentAlreadyExistsException extends Exception {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }

    public AppointmentAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}