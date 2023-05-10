package me.smartstore.exception;

import me.smartstore.util.Message;

public class InvalidSortTypeException extends RuntimeException {

    public InvalidSortTypeException() {
        super(Message.ERR_MSG_INVALID_SORT_TYPE);
    }

    public InvalidSortTypeException(String message) {
        super(message);
    }
}

