package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    //singleton
    private static Groups allGroups;

    private Groups() {
    }

    public static Groups getInstance(){
        if(allGroups == null){
            allGroups = new Groups();
        }
        return allGroups;
    }

    public Group find(GroupType groupType){
        for (Group group : arrays){
            if(group.getGroupType() == groupType){
                return group;
            }
        }
        return null;
    }


}
