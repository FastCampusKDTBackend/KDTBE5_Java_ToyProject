package me.smartstore.exception;

public class InputFormatException extends RuntimeException {
	public InputFormatException() {
		super("입력 형식이 잘못되었습니다.");
	}

	public InputFormatException(String message) {
		super(message);
	}
}

