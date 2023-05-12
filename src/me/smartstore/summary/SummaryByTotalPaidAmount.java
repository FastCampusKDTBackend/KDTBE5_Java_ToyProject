package me.smartstore.summary;

import me.smartstore.customer.Customer;

import java.util.Comparator;

public class SummaryByTotalPaidAmount extends SortedSummaryTemplate {

    private static class InstanceHolder {
        private static final SummaryByTotalPaidAmount INSTANCE = new SummaryByTotalPaidAmount();
    }
    public static SummaryByTotalPaidAmount getInstance() { return InstanceHolder.INSTANCE; }
    private SummaryByTotalPaidAmount() {}

    protected Comparator<Customer> getComparator(Order order) {
        return (order == Order.ASCENDING) ? Order.ORDER_BY_TOTAL_PAID_AMOUNT_ASC
                                          : Order.ORDER_BY_TOTAL_PAID_AMOUNT_DEC;
    }
}
