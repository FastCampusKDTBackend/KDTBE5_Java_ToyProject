package handler.exception;

import resources.Message;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super(Message.ERR_MSG_DUPLICATE_ID);
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
