package me.smartstore.exception;

public class InputRangeException extends RuntimeException{
    public InputRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_RANGE.getMessage());
    }

    public InputRangeException(String message){
        super(message);
    }
}
