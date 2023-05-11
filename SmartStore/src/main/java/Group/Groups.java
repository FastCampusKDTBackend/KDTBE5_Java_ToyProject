package Group;

import Arrays.MyArray;

public class Groups extends MyArray<Group> {

    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {

    }

    public void init() {
        Group none = new Group(new Parameter(),GroupType.NONE);
        Group general = new Group(new Parameter(),GroupType.GENERAL);
        Group vip = new Group(new Parameter(),GroupType.VIP);
        Group vVip = new Group(new Parameter(),GroupType.VVIP);

        allGroups.add(none);
        allGroups.add(general);
        allGroups.add(vip);
        allGroups.add(vVip);
    }

    public Group findGroup(GroupType groupType) {
        int index = 0;
        for (int i = 0; i < allGroups.size; i++) {
            if (allGroups.get(i).getGroupType().equals(groupType)) {
                index = i;
            }
        }
        return allGroups.get(index);
    }
    public boolean isExist(GroupType groupType) {
        Group target = findGroup(groupType);
        if (target.getParameter().getMinPay() != null && target.getParameter().getMinTime() != null) {
            return true;
        }
        return false;
    }
}
