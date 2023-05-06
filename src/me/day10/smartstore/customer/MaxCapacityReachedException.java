package me.day10.smartstore.customer;

import me.day10.smartstore.AppException;

public class MaxCapacityReachedException extends AppException {
    public MaxCapacityReachedException(String msg) { super(msg); }
}
