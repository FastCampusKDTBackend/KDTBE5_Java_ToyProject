package handler.exception;

import static resources.Message.ERR_MSG_INVALID_GROUP_EMPTY;

public class NoSuchGroupException extends RuntimeException {
    public NoSuchGroupException() {
        super(ERR_MSG_INVALID_GROUP_EMPTY);
    }

    public NoSuchGroupException(String message) {
        super(message);
    }
}
