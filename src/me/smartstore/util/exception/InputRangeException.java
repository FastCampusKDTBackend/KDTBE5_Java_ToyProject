package me.smartstore.util.exception;

import me.smartstore.util.Message;

public class InputRangeException extends RuntimeException {
    public InputRangeException() {
        super(Message.ERR_MSG_INVALID_INPUT_RANGE);
    }

    public InputRangeException(String message) {
        super(message);
    }

    // cause 기존 예외
    // 예외 전환을 위해 Throwable 사용
    public InputRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputRangeException(Throwable cause) {
        super(cause);
    }

    protected InputRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
