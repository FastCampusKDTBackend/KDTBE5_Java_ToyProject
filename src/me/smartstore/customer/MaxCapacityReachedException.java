package me.smartstore.customer;

public class MaxCapacityReachedException extends IllegalStateException {
    public MaxCapacityReachedException(String msg) { super(msg); }
}
