package com.smartstore.customer;

import com.smartstore.util.CustomList;

import java.util.Arrays;
import java.util.Optional;

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
    private final CustomList<Customer> customerList = new CustomList();

    public CustomList<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getCustomerById(String id){
        if(customerList.size() == 0){
            return null;
        }
        Optional<Customer> customerOptional = Arrays.stream(customerList.toArray(new Customer[customerList.size()]))
            .filter(customer -> id.equals(customer.getCustomerId()))
            .findFirst();
        return customerOptional.orElse(null);
    }

    public void updateMembership(){
        if(customerList.size() == 0){
            return;
        }
        Customer[] customerArray = customerList.toArray(new Customer[customerList.size()]);
        for(Customer customer : customerArray){
            customer.setMembership(customer.getUsageTime(), customer.getPaymentAmount());
        }
    }

}
