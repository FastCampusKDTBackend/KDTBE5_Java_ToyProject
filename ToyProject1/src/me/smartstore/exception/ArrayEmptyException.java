package me.smartstore.exception;

public class ArrayEmptyException extends RuntimeException {
	public ArrayEmptyException() {
		super("사용자가 없습니다. 사용자를 추가해주세요.");
	}
  
	public ArrayEmptyException(String message) {
		super(message);
	}

}

