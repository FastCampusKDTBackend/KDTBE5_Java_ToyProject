package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;

public class Validator {
    private static Validator validator;
    private Customers customers;

    private Validator() {
        customers = Customers.getInstance();
    }

    public static Validator getInstance() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    public boolean isCustomerListEmpty() {
        MyArrayList<Customer> customerList = customers.getCustomers();
        System.out.println("등록된 고객 정보가 존재하지 않습니다.");
        return customerList.isEmpty();
    }
}
