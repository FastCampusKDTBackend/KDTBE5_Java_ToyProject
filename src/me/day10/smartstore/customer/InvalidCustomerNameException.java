package me.day10.smartstore.customer;

public class InvalidCustomerNameException extends IllegalArgumentException {
    public InvalidCustomerNameException(String msg) { super(msg); }
}
