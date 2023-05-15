package me.smartstore.group;

import me.smartstore.collections.MyArrayList;

public class Groups {
    private MyArrayList<Group> groups = new MyArrayList<>();
    private static Groups instance;

    private Groups() {
        groups.add(new Group(GroupType.GENERAL, new Parameter()));
        groups.add(new Group(GroupType.VIP, new Parameter()));
        groups.add(new Group(GroupType.VVIP, new Parameter()));
    }

    public static Groups getInstance() {
        if (instance == null) {
            instance = new Groups();
        }
        return instance;
    }

    public MyArrayList<Group> getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "groups=" + groups +
                '}';
    }
}
