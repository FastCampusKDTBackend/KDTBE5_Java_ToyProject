package me.smartstore.group;

import me.smartstore.collections.DArray;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Groups extends DArray<Group> {
    private static Groups groups;

    private Groups() {
    }

    public static Groups getInstance() {
        if (groups == null) {
            groups = new Groups();
            groups.add(new Group(GroupType.GENERAL));
            groups.add(new Group(GroupType.VIP));
            groups.add(new Group(GroupType.VVIP));
        }
        return groups;
    }

    public Group findByGroupType(GroupType groupType){
        for (int i = 0; i < this.size; i++) {
            if (this.get(i).getGroupType() == groupType) {
                return this.get(i);
            }
        }
        throw new NoSuchElementException();
    }
}
