package me.smartstore.sort;

import me.smartstore.customer.Customer;

import java.util.Comparator;

public interface SortedCustomerImpl extends Comparator<Customer> {
    int compare(Customer o1, Customer o2);

    @Override
    default Comparator<Customer> reversed() {
        return Comparator.super.reversed();
    }
}
