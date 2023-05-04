package me.smartstore.exception;

import me.smartstore.util.ErrorMessage;
public class InputFormatException extends RuntimeException {
    public InputFormatException() {
        super(ErrorMessage.ERR_MSG_INVALID_INPUT_FORMAT);
    }

    public InputFormatException(String message) {
        super(message);
    }
}
