package me.smartstore.exception;

import me.smartstore.util.Message;

public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException(){
        super(Message.ERR_MSG_INVALID_ARR_EMPTY);
    }
    public EmptyArrayException(String message){
        super(message);
    }
}
