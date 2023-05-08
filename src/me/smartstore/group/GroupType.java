package me.smartstore.group;

public enum GroupType {

    N("N", "NONE"), G("G", "GENERAL"),
    V("V", "VIP"), VV("VV", "VVIP");

    private String initial;
    private String fullName;

    GroupType(String initial, String fullName) {
        this.initial = initial;
        this.fullName = fullName;
    }

    public static GroupType getGroupType(String groupType) {
        for(GroupType type : values()) {
            if (type.initial.equals(groupType) || type.fullName.equals(groupType)) return type;
        }

        return null;
    }

    @Override
    public String toString() {
        return fullName + "(" + initial + ")";
    }
}
