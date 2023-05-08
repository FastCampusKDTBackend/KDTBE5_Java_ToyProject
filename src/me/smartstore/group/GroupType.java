package me.smartstore.group;

public enum GroupType {

    N("N", "NONE", false), G("G", "GENERAL", true),
    V("V", "VIP", true), VV("VV", "VVIP", true);

    private String initial;
    private String fullName;
    private boolean setFlag;

    GroupType(String initial, String fullName, boolean setFlag) {
        this.initial = initial;
        this.fullName = fullName;
        this.setFlag = setFlag;
    }

    public static GroupType getGroupType(String groupType) {
        for(GroupType type : values()) {
            if ((type.initial.equals(groupType) || type.fullName.equals(groupType)) && type.setFlag) return type;
        }

        return null;
    }

    @Override
    public String toString() {
        return fullName + "(" + initial + ")";
    }

    public static String showGroupType() {
        StringBuilder concat = new StringBuilder();

        for (GroupType type : values()) {
            if (!type.setFlag) continue;

            concat.append(type + ", ");
        }

        return concat.substring(0, concat.length() - 2);
    }

    public static boolean isElement(String groupType) {
        if (getGroupType(groupType) == null) {
            return false;
        } else {
            return true;
        }
    }
}
