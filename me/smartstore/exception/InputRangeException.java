package me.smartstore.exception;

public class InputRangeException extends RuntimeException {
    public InputRangeException() {
    }

    public InputRangeException(String message) {
        super(message);
    }

    public InputRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputRangeException(Throwable cause) {
        super(cause);
    }
}
