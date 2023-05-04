package me.smartstore.exception;

import me.smartstore.exception.constant.Message;

public class InputTypeException extends RuntimeException{
    public InputTypeException() {
        super(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
    }

    public InputTypeException(String message) {
        super(message);
    }
}
