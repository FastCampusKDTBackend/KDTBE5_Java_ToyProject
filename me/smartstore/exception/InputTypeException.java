package me.smartstore.exception;

public class InputTypeException extends RuntimeException {
  public InputTypeException() {
    super("잘못된 값입니다. 다시 시도해주세요.");
  }
  
  public InputTypeException(String message) {
    super(message);
  }
}
