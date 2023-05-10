package domain.group;

import util.arrays.MyArray;

import java.util.Objects;

public class Groups extends MyArray<Group> {
    // singleton
    private static Groups allGroups;

    public static Groups getInstance() {
        if (Objects.isNull(allGroups)) {
            allGroups = new Groups();
            allGroups.add(new Group(0, 0, GroupType.NONE));
        }
        return allGroups;
    }

    private Groups() {
    }

    public Group find(GroupType groupType) {
        for (int i = 0; i < allGroups.size; i++) {
            if (allGroups.get(i).getGroupType() == groupType)
                return allGroups.get(i);
        }
        return null;
    }
}
