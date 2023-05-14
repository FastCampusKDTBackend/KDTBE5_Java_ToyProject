package handler.exception;

import static resources.Message.ERR_MSG_INVALID_GROUP;

public class NoSuchGroupException extends RuntimeException {
    public NoSuchGroupException() {
        super(ERR_MSG_INVALID_GROUP);
    }

    public NoSuchGroupException(String message) {
        super(message);
    }
}
