package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance(){
        if (allGroups == null){
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group findGroupByGroupType(GroupType groupType) {
        for (int i = 0; i < allGroups.size; i++) {
            if (allGroups.get(i).getGroupType() == groupType) {
                return allGroups.get(i);
            }
        }
        return null;
    }

    public void viewGroupTimeAndPayByGroupType(GroupType groupType){

        if ( findGroupByGroupType(groupType) == null ) {
            System.out.println("Group : " + groupType +
                    " ( Time : null, Pay : null )"
            );
        } else {
            System.out.println("Group : " + groupType +
                    " ( Time : " + findGroupByGroupType(groupType).getParameter().getMinTime() + ", " +
                    "Pay : " + findGroupByGroupType(groupType).getParameter().getMinPay() + " )"
            );
        }
        System.out.println(findGroupByGroupType(groupType));
    }
}
