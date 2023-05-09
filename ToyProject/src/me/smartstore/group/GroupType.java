package me.smartstore.group;

import me.smartstore.exception.InputRangeException;

// 고객 분류 타입
public enum GroupType {

    NONE("해당없음"), GENERAL("일반고객"), VIP("우수고객"), VVIP("최우수고객"),
    N("해당없음"), G("일반고객"), V("우수고객"), VV("최우수고객");

    String groupType = "";

    GroupType(String groupType) {
        this.groupType = groupType;
    }

    public GroupType replaceFullName() {
        if (this == N) return NONE;
        if (this == G) return GENERAL;
        if (this == V) return VIP;
        if (this == VV) return VVIP;
        return this;
    }

    public static int getGroupTypeLevel(Group group) {
        GroupType type = group.getGroupType();
        if (type==NONE) return 0;
        else if (type==GENERAL) return 1;
        else if (type==VIP) return 2;
        else if (type==VVIP) return 3;
        else return 0;
    }
}
