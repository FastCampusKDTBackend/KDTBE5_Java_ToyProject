package domain.group;

import util.DArray;

public class Groups extends DArray<Group> {
    // singleton
    private static Groups groups;

    public static Groups getInstance() {
        if (groups == null) {
            groups = new Groups();
        }
        return groups;
    }

    private Groups() {
    }

    public Group findGroup(GroupType groupType) {
        int index = 0;
        for (int i = 0; i < groups.size; i++) {
            if (groups.get(i).getGroupType() == groupType) {
                index = i;
                System.out.println("findGroup index : " + index);
            }
        }
        return groups.get(index);
    }
}
