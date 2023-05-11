package me.smartstore.sort;

import me.smartstore.customer.Customer;

public class SortedCustomerTime implements SortedCustomerImpl {
    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.getSpentTime() != null && o2.getSpentTime() != null)
            return o1.getSpentTime().compareTo(o2.getSpentTime());
        else
            return 0;
    }
}
