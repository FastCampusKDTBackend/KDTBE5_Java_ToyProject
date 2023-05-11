package me.smartstore.exception;


import me.smartstore.util.Message;

public class InputExitException extends RuntimeException {
    public InputExitException() {
        super(Message.INFO_INPUT_EXIT_FLAG);
    }

    public InputExitException(String message) {
        super(message);
    }
}
