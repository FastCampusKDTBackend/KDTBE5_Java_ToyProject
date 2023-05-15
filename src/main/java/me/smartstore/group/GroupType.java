package me.smartstore.group;

public enum GroupType {
    General,
    VIP,
    VVIP;

    private static final GroupType[] groupTypes = GroupType.values();

    public static GroupType getGroupType(int i) {
        return groupTypes[i];
    }
}