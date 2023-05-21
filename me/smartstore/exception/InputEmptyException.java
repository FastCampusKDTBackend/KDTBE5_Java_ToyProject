package me.smartstore.exception;

public class InputEmptyException extends RuntimeException {
  public InputEmptyException() {
    super("입력값은 공백이 될 수 없습니다. 다시 시도해주세요.");
  }
  
  public InputEmptyException(String message) {
    super(message);
  }
}
