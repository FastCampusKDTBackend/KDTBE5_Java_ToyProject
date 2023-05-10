package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

public class Customers extends DArray<Customer> {
    private final Groups allGroups = Groups.getInstance();
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public void refresh() {
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            Group group = findGroup(customer.getTotalTime(), customer.getTotalPay());
            customer.setGroup(group);
        }
    }

    public Group findGroup(int totalTime, int totalPay){
        try {
            if(totalTime >= allGroups.find(GroupType.VVIP).getParameter().getMinTime() && totalPay>=allGroups.find(GroupType.VVIP).getParameter().getMinPay()){
                return allGroups.find(GroupType.VVIP);
            } else if (totalTime >= allGroups.find(GroupType.VIP).getParameter().getMinTime() && totalPay>=allGroups.find(GroupType.VIP).getParameter().getMinPay()) {
                return allGroups.find(GroupType.VIP);
            } else if (totalTime >= allGroups.find(GroupType.GENERAL).getParameter().getMinTime() && totalPay>=allGroups.find(GroupType.GENERAL).getParameter().getMinPay()) {
                return allGroups.find(GroupType.GENERAL);
            } else{
                return allGroups.find(GroupType.NONE);
            }
        } catch (NullPointerException e){
            return null;
        }
    }
}