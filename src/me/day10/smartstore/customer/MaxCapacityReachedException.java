package me.day10.smartstore.customer;

public class MaxCapacityReachedException extends IllegalStateException {
    public MaxCapacityReachedException(String msg) { super(msg); }
}
