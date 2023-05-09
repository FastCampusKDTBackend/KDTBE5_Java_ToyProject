package me.smartstore.customer;

import java.util.Arrays;

public class Customers {
    private static Customers instance;
    private Customer[] customers;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + Arrays.toString(customers) +
                '}';
    }
}
