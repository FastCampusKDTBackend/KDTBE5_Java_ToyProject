package me.smartstore.exception;

public class InputEmptyException extends RuntimeException {
	public InputEmptyException() {
		super("입력해주세요.");
	}
  
	public InputEmptyException(String message) {
		super(message);
	}

}
