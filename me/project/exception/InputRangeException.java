package me.project.exception;

public class InputRangeException extends RuntimeException{
    public InputRangeException() {
        super();
    }

    public InputRangeException(String message) {
        super(message);
    }
}