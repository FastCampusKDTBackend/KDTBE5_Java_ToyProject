package me.smartstore.exception;

public class InputEmptyException extends RuntimeException{
    public InputEmptyException() {
        super(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
    }

    public InputEmptyException(String message) {
        super(message);
    }
}