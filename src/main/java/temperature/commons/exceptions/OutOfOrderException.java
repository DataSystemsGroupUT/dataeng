package temperature.commons.exceptions;

public class OutOfOrderException extends Exception{
    public OutOfOrderException() {
    }

    public OutOfOrderException(String message) {
        super(message);
    }
}
