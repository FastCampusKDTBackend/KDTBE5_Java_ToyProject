package me.smartstore.exception;

import me.smartstore.exception.constant.Message;

public class InputEndException extends RuntimeException{
    public InputEndException() {
        super(Message.ERR_MSG_INPUT_END.getMessage());
    }

    public InputEndException(String message) {
        super(message);
    }
}
