package me.smartstore.exception;

import me.smartstore.util.Message;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException() {
        super(Message.ERR_MSG_NULL_ELEMENT);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    public ElementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
