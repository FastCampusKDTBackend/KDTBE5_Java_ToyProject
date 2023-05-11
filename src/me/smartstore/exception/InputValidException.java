package me.smartstore.exception;

import me.smartstore.util.Message;

public class InputValidException extends RuntimeException{
    public InputValidException() {
        super(Message.ERR_MSG_INVALID_INPUT_RANGE);
    }

    public InputValidException(String s) {
        super(s);
    }
}
