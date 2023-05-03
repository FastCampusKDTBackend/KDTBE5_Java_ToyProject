package me.smartstore.customer;

import me.smartstore.arrays.MyArray;

public class Customers extends MyArray<Customer> {
    private static Customers allCustomer;

    public static Customers getInstance() {
        if (allCustomer == null) {
            allCustomer = new Customers();
        }
        return allCustomer;
    }

    private Customers() {
    }

    public void refresh(){};
}


