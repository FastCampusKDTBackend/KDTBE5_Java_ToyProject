package me.smartstore.summary;

import me.smartstore.customer.Customer;

import java.util.Comparator;

public abstract class SortedSummaryTemplate extends Summary {

    @Override
    public String get(Order order) {
        Comparator<Customer> cmp = getComparator(order);
        repository.sort(cmp);
        return getSummary();
    }

    protected abstract Comparator<Customer> getComparator(Order order);
}
