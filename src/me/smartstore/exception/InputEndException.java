package me.smartstore.exception;

public class InputEndException extends RuntimeException{
    public InputEndException() {
        super(Message.ERR_MSG_INPUT_END.getMessage());
    }

    public InputEndException(String message) {
        super(message);
    }
}
