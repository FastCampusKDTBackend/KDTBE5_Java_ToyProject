package me.smartstore.exception;

import me.smartstore.utils.Message;

public class ParameterArrEmptyException extends RuntimeException {

    public ParameterArrEmptyException() {
        super(Message.ERR_MSG_INVALID_PARAMETER_ARR_EMPTY);
    }

    public ParameterArrEmptyException(String message) {
        super(message);
    }
}
