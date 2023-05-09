package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

public class Customers extends DArray<Customer> {
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null){
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public void refresh(Groups groups) {


    }

    public void viewCustomerData(){
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            System.out.println("No. " + num + " => "+ allCustomers.get(i));
            num++;
        }
    }

    public void viewCustomerData(GroupType groupType) {
        int num = 1;
        for (int i = 0; i < allCustomers.size; i++){
            if( allCustomers.get(i).getGroup() == groupType) {
                System.out.println("No. " + num + " => " + allCustomers.get(i));
                num++;
            }
        }
    }


}
