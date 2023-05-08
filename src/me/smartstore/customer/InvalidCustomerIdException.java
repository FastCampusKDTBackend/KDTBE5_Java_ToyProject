package me.smartstore.customer;

public class InvalidCustomerIdException extends IllegalArgumentException {
    public InvalidCustomerIdException(String msg) { super(msg); }
}
