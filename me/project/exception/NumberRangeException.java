package me.project.exception;

public class NumberRangeException extends RuntimeException{
    public NumberRangeException() {
        super();
    }

    public NumberRangeException(String message) {
        super(message);
    }
}
