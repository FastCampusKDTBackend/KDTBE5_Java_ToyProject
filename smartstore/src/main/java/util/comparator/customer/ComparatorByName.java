package util.comparator.customer;

import domain.customer.Customer;

import java.util.Comparator;

public class ComparatorByName<T extends Customer> implements Comparator<T> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
