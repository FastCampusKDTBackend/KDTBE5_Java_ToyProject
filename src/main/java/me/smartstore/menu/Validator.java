package me.smartstore.menu;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

public class Validator {
    private static Validator validator;
    private Customers customers;
    private Groups groups;

    private Validator() {
        customers = Customers.getInstance();
        groups = Groups.getInstance();
    }

    public static Validator getInstance() {
        if (validator == null) {
            validator = new Validator();
        }
        return validator;
    }

    public boolean isCustomerListEmpty() {
        MyArrayList<Customer> customerList = customers.getCustomers();
        if (customerList.isEmpty()) {
            System.out.println("등록된 고객 정보가 존재하지 않습니다.");
            return true;
        }
        return false;
    }

    public boolean isGroupListEmpty() {
        MyArrayList<Group> groupList = groups.getGroups();
        if (groupList.isEmpty()) {
            System.out.println("등록된 등급 기준이 존재하지 않습니다");
            return true;
        }
        return false;
    }
}
