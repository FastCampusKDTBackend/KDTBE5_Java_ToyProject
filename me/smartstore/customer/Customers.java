package me.smartstore.customer;


import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

import java.util.Arrays;

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

        Group[] fixGroup = new Group[groups.size()];
        for(int i = 0; i < groups.size(); i++) {
            fixGroup[i] = groups.get(i);
        }

        Arrays.sort(fixGroup, (o1, o2) -> o1.getParameter().getMinPay() != o2.getParameter().getMinPay() ?
                o1.getParameter().getMinPay() - o2.getParameter().getMinPay() :
                o1.getParameter().getMinTime() - o2.getParameter().getMinTime());

        for(int i = 0; i < this.size(); i++) {
            for(int j = 0; j <= fixGroup.length - 1; j++) {
                if(this.get(i).getCusTotalTime() >= fixGroup[j].getParameter().getMinTime() &&
                        this.get(i).getCusTotalPay() >= fixGroup[j].getParameter().getMinPay()) {
                    this.get(i).setGroup(fixGroup[j]);
                }
            }
        }
    }

}
