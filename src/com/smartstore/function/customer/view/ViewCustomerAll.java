package com.smartstore.function.customer.view;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.util.List;

public class ViewCustomerAll implements Handleable {

    private static ViewCustomerAll instance;

    private ViewCustomerAll(){

    }

    public static ViewCustomerAll getInstance() {
        if(instance == null){
            return new ViewCustomerAll();
        }
        return instance;
    }

    @Override
    public void run() {
        Customers customers = Customers.getInstance();
        List<Customer> customerList = customers.getCustomerList();
        if (customerList.size() == 0){
            System.out.println("No Customer Data");
            return;
        }
        System.out.println("List of Customers");
        System.out.println("======================================");
        System.out.println(customerList.toString());
        System.out.println("======================================");
    }
}
