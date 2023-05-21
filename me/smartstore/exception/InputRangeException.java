package me.smartstore.exception;

public class InputRangeException extends RuntimeException {
  public InputRangeException() {
    super("잘못된 값입니다. 다시 시도해주세요.");
  }
  
  public InputRangeException(String message) {
    super(message);
  }
}
