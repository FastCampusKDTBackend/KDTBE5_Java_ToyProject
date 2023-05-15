package jyjang.smartstore.exception;

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

    public InputEndException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
