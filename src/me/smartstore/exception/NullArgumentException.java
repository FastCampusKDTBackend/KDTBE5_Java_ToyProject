package me.smartstore.exception;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }
}