package me.smartstore.exception;

import me.smartstore.exception.constant.Message;

public class InputFormatException extends RuntimeException {
    public InputFormatException() {
        super(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
    }

    public InputFormatException(String message) {
        super(message);
    }
}
