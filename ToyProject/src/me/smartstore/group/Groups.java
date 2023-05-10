package me.smartstore.group;

import me.smartstore.arrays.DArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class Groups extends DArray<Group> {
    // singleton pattern
    private static Groups allGroups;

    public static Groups getInstance() {
        if (allGroups == null) {
            allGroups = new Groups();
        }
        return allGroups;
    }

    private Groups() {}

//    public Group find(GroupType groupType) {
//        for (Object obj : allGroups.arrays) {
//            Group group = (Group) obj;
//            if (group.getGroupType() == groupType) return group;
//        }
//        return null;
//    }

    public Group find(GroupType groupType) {
        for (int i=0; i< allGroups.size(); i++) {
            Group group = allGroups.get(i);
            if (group.getGroupType()==groupType) {
                return group;
            }
        }
        return null;
    }

//    public Group getGroupByParameter(int spentTime, int totalPay) {
//        Group res = new Group(new Parameter(0, 0), GroupType.NONE);
//        System.out.println("this : " + this);
//        for (Group group : this) {
//            System.out.println("Group test : " + group);
//            if (spentTime >= group.getParameter().getMinTime() && totalPay >= group.getParameter().getMinPay()) {
//                res = group;
//            }
//        }
//        return res;
//    }

//    public Group getGroupByParameter(int spentTime, int totalPay) {
//        Group res = null;
//        for (Group group : this) {
//            if (spentTime >= group.getParameter().getMinTime() &&
//            totalPay >= group.getParameter().getMinPay()) {
//                if (res == null || group.getParameter().getMinTime() > res.getParameter().getMinTime()) {
//                    res = group;
//                }
//            }
//        }
//        return res != null ? res : new Group(new Parameter(0, 0), GroupType.NONE);
//    }

//    @Override
//    public Iterator<Group> iterator() {
//        return Arrays.stream(this.arrays).iterator();
//    }

//    @Override
//    public Iterator<Group> iterator() {
//        return IntStream.range(0, size)
//                .mapToObj(i -> arrays[i])
//                .filter(Objects::nonNull)
//                .map(obj -> (Group) obj)
//                .iterator();
//    }

//    @Override
//    public Iterator<Group> iterator() {
//        return Arrays.stream(arrays)
//                .filter(Objects::nonNull)
//                .map(obj -> (Group) obj)
//                .iterator();
//    }


}
