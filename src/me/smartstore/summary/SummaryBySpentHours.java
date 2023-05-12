package me.smartstore.summary;

import me.smartstore.customer.Customer;

import java.util.Comparator;

public class SummaryBySpentHours extends SortedSummaryTemplate {

    private static class InstanceHolder {
        private static final SummaryBySpentHours INSTANCE = new SummaryBySpentHours();
    }
    public static SummaryBySpentHours getInstance() { return InstanceHolder.INSTANCE; }
    private SummaryBySpentHours() {}

    protected Comparator<Customer> getComparator(Order order) {
        return (order == Order.ASCENDING) ? Order.ORDER_BY_SPENT_HOURS_ASC
                                          : Order.ORDER_BY_SPENT_HOURS_DEC;
    }
}
