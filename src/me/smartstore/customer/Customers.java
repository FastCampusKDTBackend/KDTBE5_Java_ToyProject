package me.smartstore.customer;

import me.smartstore.arrays.DArray;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Group;
import me.smartstore.group.Parameter;
import me.smartstore.customer.Customer;
import me.smartstore.util.Util;


import static me.smartstore.group.Groups.allGroups;

public class Customers extends DArray<Customer> {

    // singleton
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private final Groups allGroups = Groups.getInstance();

    private Customers() {}

    public void setCustomers(Customer[] customers) {
        this.arrays = customers;
        capacity = customers.length;
        size = customers.length;
    }

    public Customer[] getCustomers() {
        return arrays;
    }


    public Customers findCustomers(GroupType type) {
        Customers custs = new Customers();

        for(int i = 0; i < size; ++i) {
            Customer cust = get(i);
            if (cust == null) return null;

            Group grp = cust.getGroup();
            if (type == GroupType.NONE) { // Customer Group == null =>
                if (grp == null || grp.getGroupType() == null || grp.getGroupType() == GroupType.NONE) {
                    custs.add(cust);
                }
            } else if (grp != null && grp.getGroupType() == type) {
                custs.add(cust);
            }

        }

        return custs;
    }

    public Customers findCustomers(Group grp) {
        if (grp != null) {
            if (grp.getGroupType() != null) {
                return findCustomers(grp.getGroupType());
            } else {
                System.out.println("Customers.findCustomers() Error : No group type.");
                return null;
            }
        } else {
            System.out.println("Customers.findCustomers() Error : No group.");
            return null;
        }
    }

    // refresh 함수가 호출되는 경우
    // 1. 분류기준 바뀔 때
    // 2. 새로운 고객이 들어올 때
    public void refresh(Groups groups) {
        if (groups == null) return;

        for (int i = 0; i < size; i++) {
            Customer cust = arrays[i];
            Group grp = findGroupFor(cust);
            if (grp == null) {
                grp = groups.find(GroupType.NONE);
            }
            cust.setGroup(grp);
        }

    }

    public Group findGroupFor(Customer cust) {
        return cust.getGroup();
    }

}
