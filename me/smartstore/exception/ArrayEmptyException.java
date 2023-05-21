package me.smartstore.exception;

public class ArrayEmptyException extends RuntimeException {
  public ArrayEmptyException() {
    super("고객이 없습니다. 다시 시도해주세요.");
  }
  
  public ArrayEmptyException(String message) {
    super(message);
  }
}
