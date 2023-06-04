package handler.exception;

import static resources.Message.ERR_MSG_NULL_ARR_ELEMENT;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException() {
        super(ERR_MSG_NULL_ARR_ELEMENT);
    }
    public NullArgumentException(String message) {
        super(message);
    }

    public NullArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentException(Throwable cause) {
        super(cause);
    }

    public NullArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
