package me.smartstore.group;

import me.smartstore.collections.MyArrayList;

public class Groups {
    private MyArrayList<Group> groups = new MyArrayList<>();
    private static Groups instance;

    private Groups() {
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
