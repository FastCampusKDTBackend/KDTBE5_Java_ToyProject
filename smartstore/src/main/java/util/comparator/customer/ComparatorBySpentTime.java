package util.comparator.customer;

import domain.customer.Customer;

import java.util.Comparator;

public class ComparatorBySpentTime<T extends Customer> implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getSpentTime() - o2.getSpentTime();
    }
}
