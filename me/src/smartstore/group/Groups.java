package smartstore.group;

import smartstore.arrays.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

    public Group find(GroupType groupType) {
        for (Group group : arrays) {
            if (group.getGroupType() == groupType) { // enum의 비교는 equals가 아님
                return group;
            }
        }
        return null;
    }
}
