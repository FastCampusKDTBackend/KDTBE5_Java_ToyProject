package me.smartstore.group;

import me.smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance(){
        if(allGroups == null)
            allGroups = new Groups();
        return allGroups;
    }

    private Groups(){}

    public Group find(GroupType groupType){
        for(int i = 0; i < allGroups.size(); i++){
            if(allGroups.get(i).getGroupType() == groupType){
                return allGroups.get(i);
            }
        }
//        for(Group group : arrays){
//            if(group.getGroupType() == groupType){ //enum의 비교는 equals가 아님 -> 상수이기 때문
//                return group;
//            }
//        }

        return null;

    }
}
