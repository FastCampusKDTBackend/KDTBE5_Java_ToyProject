package me.smartstore.exception;

import me.smartstore.util.Message;

public class ElementDuplicateException extends RuntimeException {
	public ElementDuplicateException() {
		super(Message.ERR_ELEMENT_DUPLICATE);
	}

	public ElementDuplicateException(String message) {
		super(message);
	}
}
