package me.smartstore.summary;

import me.smartstore.customer.Customer;

import java.util.Comparator;

public class SummaryByName extends SortedSummaryTemplate {

    private static class InstanceHolder {
        private static final SummaryByName INSTANCE = new SummaryByName();
    }
    public static SummaryByName getInstance() { return InstanceHolder.INSTANCE; }
    private SummaryByName() {}

    protected Comparator<Customer> getComparator(Order order) {
        return (order == Order.ASCENDING) ? Order.ORDER_BY_NAME_ASC
                                          : Order.ORDER_BY_NAME_DEC;
    }
}
