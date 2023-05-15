package me.smartstore.customer;

import me.smartstore.collections.MyArrayList;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import java.util.Arrays;

public class Customers {
    private static Customers instance;
    private Groups groups;
    private final MyArrayList<Customer> customers = new MyArrayList<>();

    private Customers() {
        groups = Groups.getInstance();
    }

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public void refreshCustomersGroup() {
        MyArrayList<Group> groupList = groups.getGroups();

        for (int i = 0; i < customers.size(); i++) {
            Customer currentCustomer = customers.get(i);
            int hours = currentCustomer.getHours();
            int totalAmount = currentCustomer.getTotalAmount();

            for (int j = 0; j < groupList.size(); j++) {
                int minimumHours = groupList.get(i).getParameter().getMinimumHours();
                int minimumTotalAmount = groupList.get(i).getParameter().getMinimumTotalAmount();
                if (hours < minimumHours || totalAmount < minimumTotalAmount) {
                    currentCustomer.setGroup(GroupType.getGroupType(i));
                    return;
                }
            }
            currentCustomer.setGroup(GroupType.VVIP);
        }
    }

    public MyArrayList<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + customers +
                '}';
    }
}
