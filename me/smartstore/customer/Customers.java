package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Group;

import java.util.Arrays;
import java.util.Comparator;


public class Customers extends DArray<Customer> {
    private final Groups allGroups = Groups.getInstance();
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }
    private Group find(int totalTime, int totalPay) {
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
    private Customers() {}

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때df
    public void refresh() {
        for(int i=0; i<allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            Group group = find(customer.getCusTotalTime(), customer.getCusTotalPay());
            customer.setGroup(group);
;        }
    }

    public Customer[] seperateGroup(GroupType groupType) {
        int counter  = 0;
        for(int i = 0; i < allCustomers.size(); i++) {
            if(allCustomers.get(i).getGroup() == null) continue;
            if(allCustomers.get(i).getGroup().getGroupType().equals(groupType)) counter++;
        }
        Customer[] arrayGroup = new Customer[counter];
        int index = 0;

        for(int i = 0; i < allCustomers.size(); i++) {
            if(allCustomers.get(i).getGroup() == null) continue;
            if(allCustomers.get(i).getGroup().getGroupType().equals(groupType)) {
                arrayGroup[index] = allCustomers.get(i);
                index++;
            }
        }
        return arrayGroup;
    }

    public void sortName(Customer[] value, int scount) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getCusName().compareTo(o2.getCusName()) * scount);
            }
        });
    }

    public void sortPay(Customer[] value, int scount) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getCusTotalPay().compareTo(o2.getCusTotalPay()) * scount);
            }
        });
    }

    public void sortTime(Customer[] value, int scount) {
        Arrays.sort(value, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return (o1.getCusTotalTime().compareTo(o2.getCusTotalPay()) * scount);
            }
        });
    }

}
