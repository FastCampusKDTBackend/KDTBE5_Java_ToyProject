package me.smartstore.exception;

import static me.smartstore.exception.constant.Message.ERR_MSG_INVALID_INPUT_RANGE;

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
