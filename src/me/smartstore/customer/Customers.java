package me.smartstore.customer;

import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.utils.MyArray;

import java.util.Arrays;
import java.util.Comparator;

public class Customers extends MyArray<Customer> {

    private static Customers allCustomers;
    private static final Groups allGroups = Groups.getInstance();

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    public Customer[] parseToArray(Customers customers) {
        Customer[] array = new Customer[customers.size()];

        for (int i = 0; i < customers.size(); i++) {
            array[i] = customers.get(i);
        }

        return array;
    }

    public Customers sort(Customers customers, Comparator<Customer> comparator, String type) {
        Customer[] customerArray = parseToArray(customers);

        if (type.equals("A") || type.equals("ASCENDING")) {
            Arrays.sort(customerArray, comparator);
        } else {
            Arrays.sort(customerArray, comparator.reversed());
        }
        customers.arrays = customerArray;

        return customers;
    }

    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);

            for (int j = 0; j < allGroups.size(); j++) {
                Group group = allGroups.get(j);

                if (customer.getCustomerTotalTime() >= group.getParameter().getMinTime() && customer.getCustomerTotalPay() >= group.getParameter().getMinPay()) {
                    customer.setGroup(group);
                }
            }
        }
    }

    public void refresh(Customer customer) {
        for (int i = 0; i < allGroups.size(); i++) {
            Group group = allGroups.get(i);

            if (customer.getCustomerTotalTime() >= group.getParameter().getMinTime() && customer.getCustomerTotalPay() >= group.getParameter().getMinPay()) {
                customer.setGroup(group);
            }
        }
    }

    public Customers findByGroup(Group group) {
        Customers findCustomers = new Customers();

        for (int i = 0; i < size(); i++) {
            if (get(i).getGroup().equals(group)) {
                findCustomers.add(get(i));
            }
        }
        return findCustomers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append("No. " + (i + 1) + " => ");
            sb.append(get(i) + "\n");
        }
        return sb.toString();
    }
}