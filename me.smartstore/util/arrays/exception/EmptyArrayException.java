package util.arrays.exception;

public class EmptyArrayException extends RuntimeException {

    public EmptyArrayException() {
        super();
    }

    public EmptyArrayException(String message) {
        super(message);
    }
}
