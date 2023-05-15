package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Groups;

public class Customers extends DArray<Customer>{

    //singleton
    private static Customers allCustomers;

    private final Groups allGroups = Groups.getInstance();

    public static Customers getInstance(){
        if(allCustomers == null)
            allCustomers = new Customers();
        return allCustomers;
    }

    private Customers(){}

    //refresh 함수가 호출되는 경우
    //1. 분류기준이 바뀔 때
    //2. 새로운 고객이 들어올 때
    public void refresh(Groups groups){
        for(int i = 0; i < allCustomers.size(); i++){
            try{
                for(int j = 0; j < groups.size(); j++){
                    if(allCustomers.get(i).getCusTotalTime() >= groups.get(j).getParameter().getMinTime()
                            && allCustomers.get(i).getCusTotalPay() >= groups.get(j).getParameter().getMinPay()){
                        allCustomers.get(i).setGroup(groups.get(j));
                    }
                }
            }catch (NullPointerException e){
                continue;
            }

        }
    }

}
