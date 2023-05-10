package me.smartstore.util.exception;

import me.smartstore.util.Message;

public class InputEmptyException extends RuntimeException {
    public InputEmptyException() {
        super(Message.ERR_MSG_INVALID_INPUT_EMPTY);
    }

    public InputEmptyException(String message) {
        super(message);
    }

    public InputEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputEmptyException(Throwable cause) {
        super(cause);
    }

    protected InputEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
