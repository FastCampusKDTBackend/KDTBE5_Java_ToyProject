package me.smartstore.customer;

import me.smartstore.collections.MyArrayList;

import java.util.Arrays;

public class Customers {
    private static Customers instance;
    private final MyArrayList<Customer> customers = new MyArrayList<>();

    private Customers(){}
    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public  MyArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + customers +
                '}';
    }
}
