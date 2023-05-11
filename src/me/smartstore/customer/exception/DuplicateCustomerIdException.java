package me.smartstore.customer.exception;

public class DuplicateCustomerIdException extends IllegalStateException {
    public DuplicateCustomerIdException(String msg) { super(msg); }
}
