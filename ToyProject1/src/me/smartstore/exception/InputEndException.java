package me.smartstore.exception;

public class InputEndException extends RuntimeException {
	public InputEndException() {
		super("이 메뉴를 종료합니다.");
	}
	
	public InputEndException(String message) {
		super(message);
	}

}

