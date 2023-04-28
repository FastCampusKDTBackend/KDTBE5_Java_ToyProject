package me.smartstore.domain.group;

import me.smartstore.utils.MyArray;

public class Groups extends MyArray<Group> {
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
            for (int i = 0; i < 4; i++) allGroups.add(new Group()); // NONE, GENERAL, VIP, VVIP (4개 생성)
            allGroups.set(0, new Group(new Parameter(0, 0), GroupType.NONE)); // NONE은 초기에 생성
        }
        return allGroups;
    }

    private Groups() {}
}
