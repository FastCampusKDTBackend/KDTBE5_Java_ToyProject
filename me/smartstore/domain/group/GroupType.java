package me.smartstore.domain.group;

import me.smartstore.exception.InputRangeException;

public enum GroupType {
    NONE, GENERAL, VIP, VVIP,
    N, G, V, VV;

    public GroupType convertToFullName() {
        if (this == N) {
            return NONE;
        } else if (this == G) {
            return GENERAL;
        } else if (this == V) {
            return VIP;
        } else if (this == VV) {
            return VVIP;
        }
        return this;
    }

    public static int getTypeIndex(GroupType groupType) {
        if (groupType == GENERAL) {
            return 0;
        } else if (groupType == VIP) {
            return 1;
        } else if (groupType == VVIP) {
            return 2;
        }
        throw new InputRangeException();
    }
}
