package com.smartstore.function.sorting;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.util.CustomList;

public class SortByName implements SortHandler {
    private static SortByName instance;

    private SortByName(){

    }

    public static SortByName getInstance(){
        if(instance == null){
            return new SortByName();
        }
        return instance;
    }

    @Override
    public void run() {
        CustomList<Customer> customerList = Customers.getInstance().getCustomerList();
        Customer[] customerArr = customerList.toArray(new Customer[customerList.size()]);


    }
}
