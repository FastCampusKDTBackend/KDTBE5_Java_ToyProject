package me.smartstore.group;

import me.smartstore.exception.InputRangeException;

// 그룹 형태를 관리
public enum GroupType {
    NONE, GENERAL, VIP, VVIP,
    N, G, V, VV;


    public GroupType replaceFullName() {
        if (this == N) return NONE;
        else if (this == G) return GENERAL;
        else if (this == V) return VIP;
        else if (this == VV) return VVIP;
        return this;
    }

    public static GroupType findGroupType(int num){
        for(GroupType group : GroupType.values()){
            if(group.ordinal() == num){
                return group;
            }
        }
        throw new NullPointerException();
    }
    public static int getTypeLevel(Group group) {
        GroupType type = group.getGroupType();
        if (type == NONE) {
            return 0;
        } else if (type == GENERAL) {
            return 1;
        } else if (type == VIP) {
            return 2;
        } else if (type == VVIP) {
            return 3;
        }
        throw new InputRangeException();
    }
}
