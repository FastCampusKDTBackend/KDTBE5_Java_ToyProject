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





    public void refresh(){
        if (allGroups.size() != 4){
            System.out.println("you need to set all Parameter.\n"+ (4-allGroups.size())+" more left");
        }
        int noneNum = 0;
        int generalNum = 0;
        int vipNum = 0;
        int vvipNum = 0;

        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getGroupType().equals(GroupType.GENERAL)){
                generalNum = i;
            } else if (allGroups.get(i).getGroupType().equals(GroupType.VIP)) {
                vipNum = i;
            } else if (allGroups.get(i).getGroupType().equals(GroupType.VVIP)) {
                vvipNum = i;
            }else{
                noneNum = i;
            }
        }


        for (int i = 0; i < allCustomers.size; i++) {
            if ((allCustomers.get(i).getCustomerTotalTime()<allGroups.get(generalNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<allGroups.get(generalNum).getParameter().getMinPay())){
                allCustomers.get(i).setGroup(allGroups.get(noneNum));
            } else if ((allCustomers.get(i).getCustomerTotalTime()<allGroups.get(vipNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<allGroups.get(vipNum).getParameter().getMinPay())) {
                allCustomers.get(i).setGroup(allGroups.get(generalNum));
            } else if ((allCustomers.get(i).getCustomerTotalTime()<allGroups.get(vvipNum).getParameter().getMinTime()) ||
                    (allCustomers.get(i).getCustomerTotalPay()<allGroups.get(vvipNum).getParameter().getMinPay())) {
                allCustomers.get(i).setGroup(allGroups.get(vipNum));
            }
            else {
                allCustomers.get(i).setGroup(allGroups.get(vvipNum));
            }
        }
    }

}
