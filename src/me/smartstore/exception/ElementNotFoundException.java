package me.smartstore.exception;

import me.smartstore.utils.Message;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException() {
        super(Message.ERR_MSG_NULL_ARR_ELEMENT);
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
