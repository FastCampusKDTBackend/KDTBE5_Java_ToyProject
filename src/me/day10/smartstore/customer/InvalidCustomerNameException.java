package me.day10.smartstore.customer;

import me.day10.smartstore.AppException;

public class InvalidCustomerNameException extends AppException {
    public InvalidCustomerNameException(String msg) { super(msg); }
}
