package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import java.lang.reflect.Parameter;

public class Customers extends DArray<Customer> {
    // singleton
    private static Customers allCustomers;
    private Customers(){}

    public static Customers getInstance(){
        if(allCustomers == null){
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    // 해당부분 완성 - SmartStoreApp 참고
    public void refresh(Groups groups){

    }

}
