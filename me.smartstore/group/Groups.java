package group;

import arrays.DArray;
import customer.Customers;

public class Groups extends DArray<Group> {
    // singleton
    public static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    public Groups() {}

    public Group find(GroupType groupType) {
        for (int i = 0; i < allGroups.size; i++) {
            if (allGroups.get(i).getGroupType() == groupType) {
                return allGroups.get(i);
            }
        }
        return null;
    }

    public void grouping(Customers customers) {
    }

    public void setParameter(GroupType groupType, Parameter parameter) {
        Group group = find(groupType);
        if (group != null) {
            group.setParameter(parameter);
        }
    }

    public Parameter viewParameter(GroupType groupType) {
        Group group = find(groupType);
        if (group != null) {
            return group.getParameter();
        }
        return null;
    }

    public void updateParameter(GroupType groupType, Integer minTime, Integer minPay) {
        Group group = find(groupType);
        if (group != null) {
            Parameter parameter = new Parameter(minTime, minPay);
            group.setParameter(parameter);
        }
    }
}
