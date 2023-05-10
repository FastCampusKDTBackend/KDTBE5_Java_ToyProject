package me.smartstore.sort;

import me.smartstore.customer.Customer;

public class SortedCustomerName implements SortedCustomerImpl {
    @Override
    public int compare(Customer o1, Customer o2) {
        if(o1.getName() != null && o2.getName() != null)
            return o1.getName().compareTo(o2.getName());
        else
            return 0;
    }
}
