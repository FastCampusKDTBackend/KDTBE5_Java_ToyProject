package me.smartstore.exception;

import me.smartstore.exception.constant.Message;

public class ArrayEmptyException extends RuntimeException {
    public ArrayEmptyException() {
        super(Message.ERR_MSG_INVALID_ARR_EMPTY.getMessage());
    }

    public ArrayEmptyException(String message) {
        super(message);
    }
}
