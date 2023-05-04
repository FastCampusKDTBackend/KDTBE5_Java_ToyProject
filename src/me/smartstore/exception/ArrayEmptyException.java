package me.smartstore.exception;

import me.smartstore.util.ErrorMessage;
public class ArrayEmptyException extends RuntimeException {
    public ArrayEmptyException() {
        super(ErrorMessage.ERR_MSG_INVALID_ARR_EMPTY);
    }

    public ArrayEmptyException(String message) {
        super(message);
    }
}
