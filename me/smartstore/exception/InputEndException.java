package me.smartstore.exception;

public class InputEndException extends RuntimeException {
  public InputEndException() {
    super("메뉴를 종료합니다.");
  }
  
  public InputEndException(String message) {
    super(message);
  }
}
