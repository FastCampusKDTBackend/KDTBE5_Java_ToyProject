package me.project.exception;

public class InputFormatException extends RuntimeException{
    public InputFormatException() {
        super();
    }

    public InputFormatException(String message) {
        super(message);
    }
}