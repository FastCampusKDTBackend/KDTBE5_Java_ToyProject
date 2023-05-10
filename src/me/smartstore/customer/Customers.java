package me.smartstore.customer;

import me.smartstore.arrays.MyArray;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.sort.SortedType;

import java.util.Arrays;

public class Customers extends MyArray<Customer> {

    private static Customers allCustomers;
    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    public void refresh(Groups groups) {
        for(int i=0; i<groups.size(); i++) {
            Parameter parameter = groups.get(i).getParameter();
            Integer minTime = parameter.getMinTime();
            Integer minPay = parameter.getMinPay();
            if(minTime == null || minPay == null)
                continue;
            for(int j=0; j< allCustomers.size(); j++) {
                Customer customer = allCustomers.get(j);
                Integer cusTime = customer.getSpentTime();
                Integer cusPay = customer.getTotalPay();
                if(cusTime == null || cusPay == null)
                    continue;
                if(cusTime >= minTime && cusPay >= minPay)
                    customer.setGroup(groups.get(i));
            }
        }
    }

    public Customer[] getCustomerArray() {
        Customer[] customers = new Customer[size];
        for (int i = 0; i < size; i++) {
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }

    public Customer[] sort(SortedType orderType, int type) {
        Customer[] customers1 = getCustomerArray();
        if(type == -1) {
            Arrays.sort(customers1, orderType.sortedCustomer.reversed());
        } else if(type == 1) {
            Arrays.sort(customers1, orderType.sortedCustomer);
        } else return customers1;
        return customers1;
    }

}
