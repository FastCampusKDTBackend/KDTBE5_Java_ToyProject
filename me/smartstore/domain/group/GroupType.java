package me.smartstore.domain.group;

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
}
