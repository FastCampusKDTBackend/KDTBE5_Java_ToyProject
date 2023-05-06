package me.day10.smartstore.customer;

public class DuplicateCustomerIdException extends IllegalStateException {
    public DuplicateCustomerIdException(String msg) { super(msg); }
}
