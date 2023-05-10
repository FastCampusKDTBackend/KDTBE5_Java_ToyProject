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

    private Groups() {}

    public void init() {
        Group none = new Group(GroupType.NONE);
        Group general = new Group(GroupType.GENERAL);
        Group vip = new Group(GroupType.VIP);
        Group vVip = new Group(GroupType.VVIP);

        groups.add(none);
        groups.add(general);
        groups.add(vip);
        groups.add(vVip);
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
    public boolean isExist(GroupType groupType) {
        Group target = findGroup(groupType);
        if (target.getParameter() != null) {
            return false;
        }
        return true;
    }
}