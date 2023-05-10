package me.smartstore.customers;

import me.smartstore.groups.Group;
import me.smartstore.groups.Groups;
import me.smartstore.util.arrays.Array;

import java.util.Arrays;
import java.util.Comparator;

public class Customers extends Array<Customer> {
    private static Customers allCustomers;
    private final Groups allGroups = Groups.getInstance();

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {};

    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);


            for (int j = 0; j < allGroups.size(); j++) {
                Group group = allGroups.get(j);

                if (group.getParmeter().getMinTime() == null || group.getParmeter().getMinPayment() == null ||
                    customer.getTotalPay() == null || customer.getTotalTime() == null) {
                    continue;
                }

                if ((customer.getTotalTime() >= group.getParmeter().getMinTime()) && (customer.getTotalPay() >= group.getParmeter().getMinPayment())) {
                    customer.setGroup(group);
                }
            }
        }
    }


    public Customer[] sortSummary(Comparator<Customer> comparator)  {
        Customer[] customerArr = initCustomers();
        Arrays.sort(customerArr, comparator);
        return customerArr;
    }

    public Customer[] initCustomers() {
        Customer[] customerArrs = new Customer[allCustomers.size()];

        for (int i = 0; i < allCustomers.size(); i++) {
            customerArrs[i] = allCustomers.get(i);
        }
        return customerArrs;
    }

    public Comparator<Customer> sortName(int num) {
        return (customer1, customer2) -> num * customer1.getcName().compareTo(customer2.getcName());
    }

    public Comparator<Customer> sortTime(int num) {
        return (customer1, customer2) -> num * customer1.getTotalTime().compareTo(customer2.getTotalTime());
    }

    public Comparator<Customer> sortPay(int num) {
        return (customer1, customer2) -> num * customer1.getTotalPay().compareTo(customer2.getTotalPay());
    }

};
