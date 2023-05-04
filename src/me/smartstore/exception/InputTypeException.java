package me.smartstore.exception;

public class InputTypeException extends RuntimeException{
    public InputTypeException() {
        super(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
    }

    public InputTypeException(String message) {
        super(message);
    }
}
