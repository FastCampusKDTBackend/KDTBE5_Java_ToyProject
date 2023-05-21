package me.smartstore.exception;

public class InputFormatException extends RuntimeException {
  public InputFormatException() {
    super("입력된 값이 잘못된 형식으로 되어있습니다. 다시 시도해주세요.");
  }
  
  public InputFormatException(String message) {
    super(message);
  }
}
