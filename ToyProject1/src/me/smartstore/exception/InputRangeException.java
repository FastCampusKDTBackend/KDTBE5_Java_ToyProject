package me.smartstore.exception;

public class InputRangeException extends RuntimeException {
	public InputRangeException() {
		super("입력 범위가 잘못되었습니다.");
	}
	
	public InputRangeException(String message) {
		super(message);
	}

}

