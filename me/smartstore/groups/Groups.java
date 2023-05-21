package me.smartstore.groups;

import me.smartstore.customers.Customer;
import me.smartstore.util.UtilMethod;

public class Groups {
    private static Groups allGroups;

    private int size;
    private Group[] groups;

    public static Groups getInstance() {
        if (allGroups == null)
            allGroups = new Groups();
        return allGroups;
    }

    public Groups() {
        this.groups = new Group[GroupType.size()];
        initialize();
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public int size() {
        return size;
    }

    public int length() {
        return groups.length;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void initialize() {
        for (int i = 0; i < GroupType.size(); i++) {
            groups[i] = new Group(GroupType.values()[i], null);
            size++;
        }
    }

    public void add(Group group) {
        Group grp = find(group.getType());
        if (grp != null) {
            update(group);
        } else {
            groups[size] = group;
            size++;
        }
    }

    public Group get(int i) {
        return groups[i];
    }

    public void update(Group group) {
        Group grp = find(group.getType());
        if (grp != null)
            grp.setParam(group.getParam());
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            if (groups[i] != null)
                System.out.println(groups[i]);
        }
    }

    public Group find(GroupType groupType) {
        for (Group grp : groups) {
            if (grp.getType() == groupType)
                return grp;
        }
        return null;
    }

    public Group findGroupFor(Customer customer) {
        if (groups == null)
            return null;
        if (UtilMethod.isAnyNUll(new Object[]{customer, customer.getSpentTime(), customer.getTotalPay()}))
            return null;
        for (int i = size - 1; i >= 0; i--) {
            if (!UtilMethod.isAnyNUll(new Object[]{groups[i], groups[i].getParam()})) {
                Parameter param = groups[i].getParam();
                if (!UtilMethod.isAnyNUll(new Object[]{param, param.getMinimumSpentTime(), param.getMinimumTotalPay()}))
                    if (customer.getSpentTime().intValue() >= param.getMinimumSpentTime().intValue() && customer
                            .getTotalPay().intValue() >= param.getMinimumTotalPay().intValue())
                        return groups[i];
            }
        }
        return null;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++)
            str = str + " " + str + "\n";
        return str;
    }
}
