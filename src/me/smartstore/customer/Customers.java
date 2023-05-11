package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

import java.lang.reflect.Parameter;

public class Customers extends DArray<Customer> {
    private final Groups allGroups = Groups.getInstance();
    // singleton
    private static Customers allCustomers;
    private Customers(){}

    public static Customers getInstance(){
        if(allCustomers == null){
            allCustomers = new Customers();
        }
        return allCustomers;
    }


    public Customer[] getCustomers(){
        Customer[] customers = new Customer[allCustomers.size()];
        for (int i = 0; i < allCustomers.size(); i++) {
            customers[i] = allCustomers.get(i);
        }
        return customers;
    }





    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    // 해당부분 완성 - SmartStoreApp 참고
    public void refresh(Groups groups){
        // 고객들 리스트 불러와서 한명씩 Customer.customerTotalTime-Group.minTime
        // Customer.customerTotalPay-Group.minPay와 비교를해서 그룹타입을 지정해준다.
        // 지정은 setGroup을 통해서 한다.
        int noneNum = 0;
        int generalNum = 0;
        int vipNum = 0;
        int vvipNum = 0;

        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getGroupType().equals(GroupType.GENERAL)){
                generalNum = i;
            } else if (groups.get(i).getGroupType().equals(GroupType.VIP)) {
                vipNum = i;
            } else if (groups.get(i).getGroupType().equals(GroupType.VVIP)) {
                vvipNum = i;
            }else{
                noneNum = i;
            }
        }


        for (int i = 0; i < allCustomers.size; i++) {
            if ((allCustomers.get(i).getCustomerTotalTime()<groups.get(generalNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<groups.get(generalNum).getParameter().getMinPay())){
                allCustomers.get(i).setGroup(groups.get(noneNum));
            } else if ((allCustomers.get(i).getCustomerTotalTime()<groups.get(vipNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<groups.get(vipNum).getParameter().getMinPay())) {
                allCustomers.get(i).setGroup(groups.get(generalNum));
            } else if ((allCustomers.get(i).getCustomerTotalTime()<groups.get(vvipNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<groups.get(vvipNum).getParameter().getMinPay())) {
                allCustomers.get(i).setGroup(groups.get(vipNum));
            }
            else {
                allCustomers.get(i).setGroup(groups.get(vvipNum));
            }
        }
    }

}
