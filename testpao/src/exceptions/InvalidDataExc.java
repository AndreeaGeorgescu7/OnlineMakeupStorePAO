package exceptions;

public class InvalidDataExc extends Exception {
    public InvalidDataExc(String cause) {
        super(cause);
    }

    public InvalidDataExc(String message, Throwable cause) {
        super(message, cause);
    }
}