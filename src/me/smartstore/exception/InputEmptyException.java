package me.smartstore.exception;

import me.smartstore.util.ErrorMessage;
public class InputEmptyException extends RuntimeException{
    public InputEmptyException() {
        super(ErrorMessage.ERR_MSG_INVALID_INPUT_EMPTY);
    }

    public InputEmptyException(String message) {
        super(message);
    }
}