package me.smartstore.exception;

import me.smartstore.exception.message.ErrorMessage;

import static me.smartstore.exception.message.ErrorMessage.ERR_MSG_INVALID_INPUT_RANGE;

public class InputRangeException extends RuntimeException {
    public InputRangeException() {
        super(ERR_MSG_INVALID_INPUT_RANGE.getMessage());
    }

    public InputRangeException(String message) {
        super(message);
    }

    public InputRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputRangeException(Throwable cause) {
        super(cause);
    }
}
