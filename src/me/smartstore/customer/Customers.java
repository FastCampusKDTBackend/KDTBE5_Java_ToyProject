package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

import java.util.Arrays;
import java.util.Comparator;

public class Customers extends DArray<Customer> {

    private final Groups allGroups = Groups.getInstance();

    // singleton

    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            Group group = findCustomerGroup(customer.getTotalTime(), customer.getTotalPay());
            customer.setGroup(group);
        }
    }

    public Group findCustomerGroup(int Time, int Pay) {
        Group group = null;
        for (int i = 0; i < allGroups.size(); i++) {
            Group groupPara = allGroups.get(i);
            if (Time >= groupPara.getParameter().getMinimumSpentTime() &&
                    Pay >= groupPara.getParameter().getMinimumTotalPay())  {
                group = groupPara;
            }
        }
        return group;
    }

    public Customer[] toArray() {
        Customer[] customer = new Customer[size];
        for (int i = 0; i < size; i++) {
            customer[i] = allCustomers.get(i);
        }
        return customer;
    }

    public Customer[] toArraySort(Comparator<Customer> comparator) {
        Customer[] customer = toArray();
        Arrays.sort(customer, comparator);
        return customer;
    }
}
