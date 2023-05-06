package me.day10.smartstore.customer;

import me.day10.smartstore.AppException;

public class InvalidCustomerIdException extends AppException {
    public InvalidCustomerIdException(String msg) { super(msg); }
}
