package me.smartstore.exception;

import me.smartstore.util.ErrorMessage;
public class InputTypeException extends RuntimeException{
    public InputTypeException() {
        super(ErrorMessage.ERR_MSG_INVALID_INPUT_TYPE);
    }

    public InputTypeException(String message) {
        super(message);
    }
}
