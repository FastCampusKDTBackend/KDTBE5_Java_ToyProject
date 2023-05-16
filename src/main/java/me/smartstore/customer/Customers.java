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

            for (int j = 0; j < groupList.size() - 1; j++) {
                int currentMinimumHours = groupList.get(j).getParameter().getMinimumHours();
                int currentMinimumTotalAmount = groupList.get(j).getParameter().getMinimumTotalAmount();
                int nextMinimumHours = groupList.get(j + 1).getParameter().getMinimumHours();
                int nextMinimumTotalAmount = groupList.get(j + 1).getParameter().getMinimumTotalAmount();
                //어떤 기준도 충족 못시킬 때
                if (hours <= currentMinimumHours || totalAmount <= currentMinimumTotalAmount) {
                    currentCustomer.setGroup(GroupType.getGroupType(j));
                    break;
                }
                if (hours <= nextMinimumHours && totalAmount <= nextMinimumTotalAmount) {
                    currentCustomer.setGroup(GroupType.getGroupType(j));
                    break;
                }
            }
            currentCustomer.setGroup(GroupType.getGroupType(groupList.size() - 1));
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
