package me.smartstore.customer.exception;

public class InvalidCustomerIdException extends IllegalArgumentException {
    public InvalidCustomerIdException(String msg) { super(msg); }
}
