package util.common.exception;

import util.common.ErrorMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(ErrorMessage.INVALID_INPUT);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
