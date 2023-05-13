package me.smartstore.util;

import me.smartstore.customer.Customer;
import me.smartstore.exception.InvalidSortTypeException;

import java.util.Comparator;

public enum SortType {
    CUSTOMER_NAME, CUSTOMER_TIME, CUSTOMER_PAYMENT;

    public Comparator<Customer> getCustomerComparator(int classificationValue) {
        if (this == CUSTOMER_NAME) {
            return (customer1, customer2) -> classificationValue * customer1.getCustomerName().compareTo(customer2.getCustomerName());
        }
        else if (this == CUSTOMER_TIME) {
            return (customer1,  customer2) -> classificationValue * customer1.getCustomerTotalTime().compareTo(customer2.getCustomerTotalTime());
        }
        else if (this == CUSTOMER_PAYMENT) {
            return (customer1, customer2) -> classificationValue * customer1.getCustomerTotalPay().compareTo(customer2.getCustomerTotalPay());
        }
        throw new InvalidSortTypeException();
    }
}
