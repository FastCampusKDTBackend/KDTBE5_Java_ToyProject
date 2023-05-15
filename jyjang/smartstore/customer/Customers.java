package jyjang.smartstore.customer;

import jyjang.smartstore.arrays.DArray;
import jyjang.smartstore.group.Group;
import jyjang.smartstore.group.GroupType;
import jyjang.smartstore.group.Groups;

public class Customers extends DArray<Customer> {

    // singleton
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

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            customer.setGroup(findGroupByCustomer(customer));
        }

    }

    public Group findGroupByCustomer(Customer customer) {

        int totalPay = customer.getTotalPay();
        int totalTime = customer.getTotalTime();

        for (int i = allGroups.size() - 1; i >= 0; i--) {
            Group group = allGroups.get(i);

            if (totalPay >= group.getParameter().getMinimumTotalPay() && totalTime >= group.getParameter().getMinimumSpentTime()) {
                return group;
            }
        }

        return null;
    }

}
