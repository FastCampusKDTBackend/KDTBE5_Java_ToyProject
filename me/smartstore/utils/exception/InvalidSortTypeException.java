package me.smartstore.utils.exception;

import static me.smartstore.utils.constant.Message.ERR_MSG_INVALID_INPUT_SORT_TYPE;

public class InvalidSortTypeException extends RuntimeException {
    public InvalidSortTypeException() {
        super(ERR_MSG_INVALID_INPUT_SORT_TYPE.getMessage());
    }

    public InvalidSortTypeException(String message) {
        super(message);
    }

    public InvalidSortTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSortTypeException(Throwable cause) {
        super(cause);
    }
}
