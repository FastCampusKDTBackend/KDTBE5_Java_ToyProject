package me.smartstore.domain.group;

import me.smartstore.domain.group.constant.GroupType;
import me.smartstore.utils.MyArray;

public class Groups extends MyArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
            allGroups.add(new Group(new Parameter(), GroupType.NONE)); // NONE은 초기에 생성
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
}
