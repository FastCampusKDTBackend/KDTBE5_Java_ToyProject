package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType) {
        for(int i = 0; i < this.size; i++) {
            if(this.get(i).getGroupType() == groupType) {
                return this.get(i);
            }
        }
        return null;
    }
}
