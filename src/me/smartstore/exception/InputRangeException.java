package me.smartstore.exception;

import me.smartstore.exception.constant.Message;

public class InputRangeException extends RuntimeException{
    public InputRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
    }

    public InputRangeException(String message){
        super(message);
    }
}
