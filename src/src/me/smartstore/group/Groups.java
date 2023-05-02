package me.smartstore.group;

import me.smartstore.arrays.MyArray;

public class Groups extends MyArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType){
        for(Group group : arrays){
            if(group.getGroupType() == groupType){
                return group;
            }
        }
        return null;
    }

}
