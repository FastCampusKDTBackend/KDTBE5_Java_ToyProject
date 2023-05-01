package me.smartstore.exception;

import static me.smartstore.exception.constant.Message.ERR_MSG_INPUT_END;

public class InputEndException extends RuntimeException {
    public InputEndException() {
        super(ERR_MSG_INPUT_END.getMessage());
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
}
