package me.smartstore.exception;

import me.smartstore.util.Message;

public class InputEmptyException extends RuntimeException {

    public InputEmptyException() {
        super(Message.ERR_INVALID_INPUT_EMPTY);
    }

    public InputEmptyException(String message) {
        super(message);
    }
}
