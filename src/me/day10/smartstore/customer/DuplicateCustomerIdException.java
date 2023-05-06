package me.day10.smartstore.customer;

import me.day10.smartstore.AppException;

public class DuplicateCustomerIdException extends AppException {
    public DuplicateCustomerIdException(String msg) { super(msg); }
}
