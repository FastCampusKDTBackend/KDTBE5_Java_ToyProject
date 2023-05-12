package me.smartstore.exception;

import me.smartstore.utils.Message;

public class CustomerArrayEmptyException extends RuntimeException {

    public CustomerArrayEmptyException() {
        super(Message.ERR_MSG_INVALID_CUSTOMER_ARR_EMPTY);
    }

    public CustomerArrayEmptyException(String message) {
        super(message);
    }
}
