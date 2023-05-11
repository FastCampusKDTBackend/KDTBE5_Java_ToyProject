package me.smartstore.sort;

public enum SortedType {
    SORTED_NAME(new SortedCustomerName()),
    SORTED_TIME(new SortedCustomerTime()),
    SORTED_PAY(new SortedCustomerPay());

    public SortedCustomerImpl sortedCustomer;

    SortedType(SortedCustomerImpl sortedCustomer) {
        this.sortedCustomer = sortedCustomer;
    }
}
