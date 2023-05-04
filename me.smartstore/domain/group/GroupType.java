package domain.group;

public enum GroupType {
    NONE(4), GENERAL(3), VIP(2), VVIP(1),
    N(4), G(3), V(2), VV(1);

    final int groupRank;

    GroupType(int groupRank) {
        this.groupRank = groupRank;
    }

    public GroupType replaceFullName() {
        if (this == N) return NONE;
        else if (this == G) return GENERAL;
        else if (this == V) return VIP;
        else if (this == VV) return VVIP;
        return this;
    }

    public int getRank(){
        return groupRank;
    }
}
