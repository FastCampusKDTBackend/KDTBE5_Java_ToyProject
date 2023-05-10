package me.smartstore.customer;

import me.smartstore.arrays.MyArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import static me.smartstore.group.GroupType.getTypeLevel;

public class Customers extends MyArray<Customer> {
    private static Customers allCustomers;
    private final Groups allGroups = Groups.getInstance();

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {
    }

    public Customer[] getCustomers() {
        Customer[] customers = new Customer[size];
        for (int i = 0; i < size; i++) {
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }


    // TODO: Refresh 메서드 구현 (새로운 고객 추가, 분류기준 변경시 작동)
    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            Group group = findGroupOfCustomer(customer.getCusTotalTime(), customer.getCusTotalPay());
            customer.setGroup(group);
        }
    }

    public Group findGroupOfCustomer(int newTime, int newAmount) {
        Group group = allGroups.get(0); // 처음에는 NONE
        for (int i = 1; i < allGroups.size(); i++) {
            Group refreshGroup = allGroups.get(i);
            int minTime = refreshGroup.getParameter().getMinTime();
            int minPay = refreshGroup.getParameter().getMinPay();

            if (newTime >= minTime && newAmount >= minPay) {
                if (getTypeLevel(group) < getTypeLevel(refreshGroup)) {
                    group = refreshGroup;
                }
            }
        }
        return group;
    }
}


