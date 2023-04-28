package me.smartstore.exception;

public class ArrayEmptyException extends RuntimeException {
    public ArrayEmptyException() {
    }

    public ArrayEmptyException(String message) {
        super(message);
    }

    public ArrayEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayEmptyException(Throwable cause) {
        super(cause);
    }
}
