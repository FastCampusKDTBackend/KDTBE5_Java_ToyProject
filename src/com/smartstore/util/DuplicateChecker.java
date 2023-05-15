package com.smartstore.util;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;

import java.util.Arrays;
import java.util.Optional;

public class DuplicateChecker {
    public static boolean isIdDuplicated(String id){
        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        Optional<Customer> customerOptional = Arrays.stream(customerList.toArray(new Customer[customerList.size()]))
                .filter(customer -> id.equalsIgnoreCase(customer.getCustomerName()))
                .findFirst();
        return customerOptional.isPresent();
    }
}
