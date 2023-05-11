package me.smartstore.utils.exception;

public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException() {
    }

    public EmptyArrayException(String message) {
        super(message);
    }
}
