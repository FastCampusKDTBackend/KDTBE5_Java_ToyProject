package me.smartstore.groups;

import me.smartstore.util.arrays.Array;

public class Groups extends Array<Group> {

    // singleton
    private static Groups allGroups;
    private Parameter parameter;

    private Groups() {}

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
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
}
