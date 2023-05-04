package me.smartstore.exception;

import me.smartstore.util.ErrorMessage;
public class InputEndException extends RuntimeException{
    public InputEndException() {
        super(ErrorMessage.ERR_MSG_INPUT_END);
    }

    public InputEndException(String message) {
        super(message);
    }
}
