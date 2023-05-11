package me.project.groups;

import me.project.customers.Customer;

public class Groups {
    private int count = 0;
    private Group[] groups;
    private static final int DEFAULT_SIZE = 4;
    private static Groups allGroups;

    public static Groups getInstance() { //싱글톤 패턴
        if(allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }
    private Groups() {
        this.groups = new Group[DEFAULT_SIZE];
        groups[0] = new Group(Grade.NONE, null);
        groups[1] = new Group(Grade.GENERAL, null);
        groups[2] = new Group(Grade.VIP, null);
        groups[3] = new Group(Grade.VVIP, null);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int length() {
        return this.groups.length;
    }

    public Group get (int i) {
        return groups[i];
    }

    public void add (Group group) {
        Group grp = this.find(group.getGrade());
        if (grp != null) {
            this.edit(group);
        } else {
            this.groups[this.count] = group;
            ++this.count;
        }
    }

    public Group find (Grade grade) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].getGrade() == grade) {
                return groups[i];
            }
        }
        return null;
    }

    public Group findGroup (Customer customer) {
        if (this.groups != null && customer != null) {
            for (int i = this.groups.length - 1; i >= 0; --i) {
                Parameter parameter = groups[i].getParameter();
                if (parameter != null && customer.getSpentTime() >= parameter.getMinimumSpentTime() && customer.getTotalPay() >= parameter.getMinimumTotalPay()) {
                    return this.groups[i];
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public void edit (Group group) {
        Group grp = this.find(group.getGrade());
        if (grp != null) {
            grp.setParameter(group.getParameter());
        }
    }
}