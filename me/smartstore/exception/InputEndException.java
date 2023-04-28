package me.smartstore.exception;

public class InputEndException extends RuntimeException {
    public InputEndException() {
    }

    public InputEndException(String message) {
        super(message);
    }

    public InputEndException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputEndException(Throwable cause) {
        super(cause);
    }
}
