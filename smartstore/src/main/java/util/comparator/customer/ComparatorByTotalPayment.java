package util.comparator.customer;

import domain.customer.Customer;

import java.util.Comparator;

public class ComparatorByTotalPayment<T extends Customer> implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getTotalPay() - o2.getTotalPay();
    }
}
