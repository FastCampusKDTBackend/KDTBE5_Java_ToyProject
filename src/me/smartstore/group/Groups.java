package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    // singleton
    public static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {
    }

    public Group find(GroupType groupType) {
        for (int i = 0; i < allGroups.size(); i++) {
            Group group = allGroups.get(i);
            if (group.getGroupType() == groupType) {
                return group;
            }
        }
        return null;
    }

    public void update(Group group) {
        Group grp = find(group.getGroupType());
        if (grp != null) {
            grp.setParameter(group.getParameter());
        }

    }
}
