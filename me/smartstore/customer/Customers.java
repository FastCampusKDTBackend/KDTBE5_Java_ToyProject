package me.smartstore.customer;


import me.smartstore.arrays.DArray;
import me.smartstore.group.Groups;

public class Customers extends DArray<Customer> {

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
    public void refresh(Groups groups) {
        for(int i = 0; i < allCustomers.size(); i++) {
            for(int j = 0; j <= groups.size() - 1; j++) {
                if(allCustomers.get(i).getCusTotalTime() >= groups.get(j).getParameter().getMinTime() &&
                allCustomers.get(i).getCusTotalPay() >= groups.get(j).getParameter().getMinPay()) {
                    allCustomers.get(i).setGroup(groups.get(j));
                }
            }
        }
    }

}