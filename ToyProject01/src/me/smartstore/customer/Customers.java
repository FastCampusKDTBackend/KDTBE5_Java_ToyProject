package me.smartstore.customer;

public class Customers extends Customer {

    private static Customers instance;

    private static final int defaultCapacity = 10;

    private Customer[] CustomerArray;
    private int capacity;

    private Customers() {

        this.CustomerArray = new Customer[defaultCapacity];
        this.capacity = defaultCapacity;
    }

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
