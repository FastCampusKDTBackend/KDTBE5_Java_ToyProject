package me.smartstore.customer.exception;

public class MaxCapacityReachedException extends IllegalStateException {
    public MaxCapacityReachedException(String msg) { super(msg); }
}
