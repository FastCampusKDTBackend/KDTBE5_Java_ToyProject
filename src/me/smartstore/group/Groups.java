package me.smartstore.group;

import me.smartstore.utils.MyArray;

public class Groups extends MyArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

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
        Group updateGroup = find(group.getGroupType());
        if (updateGroup != null)
            updateGroup.setParameter(group.getParameter());
    }
}