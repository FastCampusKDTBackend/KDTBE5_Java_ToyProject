package me.smartstore.exception;

import me.smartstore.util.Message;

public class ElementEmptyException extends RuntimeException{
	public ElementEmptyException() {
		super(Message.ERR_MSG_INVALID_ARR_EMPTY);
	}

	public ElementEmptyException(String message) {
		super(message);
	}
}
