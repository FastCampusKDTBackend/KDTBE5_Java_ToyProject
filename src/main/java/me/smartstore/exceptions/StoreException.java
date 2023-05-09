package me.smartstore.exceptions;

public class StoreException extends RuntimeException {
  private final StoreErrorCode storeErrorCode;
  private final String message;

  public StoreException() {
    this.storeErrorCode = StoreErrorCode.INTERNAL_ERROR;
    this.message = StoreErrorCode.INTERNAL_ERROR.getMessage();
  }

  public StoreException(StoreErrorCode storeErrorCode) {
    this.storeErrorCode = storeErrorCode;
    this.message = storeErrorCode.getMessage();
  }

  public StoreException(StoreErrorCode storeErrorCode, String message) {
    this.storeErrorCode = storeErrorCode;
    this.message = message;
  }

  public StoreException(String message) {
    this.storeErrorCode = StoreErrorCode.INTERNAL_ERROR;
    this.message = message;
  }

  public StoreErrorCode getErrorCode() {
    return storeErrorCode;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
