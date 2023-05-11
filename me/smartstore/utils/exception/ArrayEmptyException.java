package me.smartstore.utils.exception;

import static me.smartstore.utils.constant.Message.ERR_MSG_INVALID_ARR_EMPTY;

public class ArrayEmptyException extends RuntimeException {
    public ArrayEmptyException() {
        super(ERR_MSG_INVALID_ARR_EMPTY.getMessage());
    }

    public ArrayEmptyException(String message) {
        super(message);
    }

    public ArrayEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayEmptyException(Throwable cause) {
        super(cause);
    }
}
