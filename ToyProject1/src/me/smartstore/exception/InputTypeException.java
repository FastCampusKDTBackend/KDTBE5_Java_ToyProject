package me.smartstore.exception;

public class InputTypeException extends RuntimeException {
	public InputTypeException() {
		super("입력 타입이 잘못되었습니다.");
	}

	public InputTypeException(String message) {
		super(message);
	}

}

