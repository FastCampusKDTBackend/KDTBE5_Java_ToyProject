package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import java.util.Arrays;
import java.util.Comparator;

import static me.smartstore.group.GroupType.getGroupTypeLevel;

public class Customers extends DArray<Customer> {

    private static Customers allCustomers;
    private final Groups allGroups;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {
        this.allGroups = Groups.getInstance();
    }

    /**
     * 고객 추가 및 고객 분류기준 변경시 고객 분류 등급 재조정 해주는 역할 담당
     */
    public void refresh() {
        for (int i=0; i< allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            System.out.println("refresh start -> allCustomer size configure : " + allCustomers.size());
            Group group = findGroupOfCustomer(customer.getCustomerTotalTime(), customer.getCustomerTotalPay());
            customer.setGroup(group);
        }
    }

    /**
     * @param: spentTime
     * @param: spentMoney
     * @return: Group instance that corresponding with customer
     */
    public Group findGroupOfCustomer(int spentTime, int spentMoney) {
        Group group = allGroups.get(0);
        for (int i=1; i< allGroups.size(); i++) {
            Group pickedGroup = allGroups.get(i);
            int minSpentTime = pickedGroup.getParameter().getMinTime();
            int minSpentMoney = pickedGroup.getParameter().getMinPay();
            if (spentTime>=minSpentTime && spentMoney>=minSpentMoney) {
                if (getGroupTypeLevel(group) < getGroupTypeLevel(pickedGroup)) {
                    group = pickedGroup;
                }
            }
        }
        return group;
    }

    public Customer[] getAllCustomers() {
        Customer[] customers = new Customer[size];
        for (int i=0; i<size; i++) {
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }

    public Customer[] getSortedCustomers(Comparator<Customer> comparator) {
        Customer[] customers = getAllCustomers();
        Arrays.sort(customers, comparator);
        return customers;
    }

    //    public void refresh(Groups groups) {
//        for (Customer customer : arrays) {
//            Group group = groups.find(GroupType)
//        }
//    }

    // 파라미터가 변경되었거나 추가되는 경우, 고객 분류를 다시 해야함
//    public void refresh(Groups groups) {
//        for (Customer customer : ) {
//            Group group = groups.find
//        }
//    }
}
