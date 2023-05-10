package me.smartstore.sort;

import me.smartstore.customer.Customer;

public class SortedCustomerPay implements SortedCustomerImpl {
    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.getTotalPay() != null && o2.getTotalPay() != null)
            return o1.getTotalPay().compareTo(o2.getTotalPay());
        else
            return 0;
    }
}
