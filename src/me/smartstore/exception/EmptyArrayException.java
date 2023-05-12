package me.smartstore.exception;

import me.smartstore.utils.Message;

public class EmptyArrayException extends RuntimeException{
    public EmptyArrayException() {
        super(Message.ERR_MSG_ARR_EMPTY);
    }

    public EmptyArrayException(String message) {
        super(message);
    }
}
