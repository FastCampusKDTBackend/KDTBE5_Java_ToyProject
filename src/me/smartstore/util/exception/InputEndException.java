package me.smartstore.util.exception;

import me.smartstore.util.Message;

public class InputEndException extends RuntimeException {
    public InputEndException() {
        super(Message.ERR_MSG_INPUT_END);
    }

    public InputEndException(String message) {
        super(message);
    }

    public InputEndException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputEndException(Throwable cause) {
        super(cause);
    }

    protected InputEndException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
