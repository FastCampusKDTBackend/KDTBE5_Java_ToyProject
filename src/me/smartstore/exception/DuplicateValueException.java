package me.smartstore.exception;

import me.smartstore.util.Message;

public class DuplicateValueException extends RuntimeException{
	public DuplicateValueException() {
		super(Message.ERR_MSG_DUPLICATED_CUSTOMERID);
	}

	public DuplicateValueException(String message) {
		super(message);
	}
}
