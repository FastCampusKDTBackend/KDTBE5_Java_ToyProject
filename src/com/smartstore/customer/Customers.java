package com.smartstore.customer;

import com.smartstore.util.CustomList;

import java.util.List;

public class Customers {
    private static Customers instance;

    private Customers() {

    }

    public static Customers getInstance(){
        if(instance == null){
            instance = new Customers();
        }
        return instance;
    }
    private CustomList<Customer> customerList = new CustomList();

    public CustomList<Customer> getCustomerList() {
        return customerList;
    }

    public void refresh(){

    }
}
