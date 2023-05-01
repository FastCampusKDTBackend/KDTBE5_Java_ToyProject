package me.day10.smartstore.group;

public enum GroupType {

    GENERAL("G", null),
    VIP("V", null),
    VVIP("VV", null);

    GroupType(String shortcut, GroupTypeParameter groupTypeParameter) {
        this.shortcut = shortcut;
        this.groupTypeParameter = groupTypeParameter;
    }

    private final GroupTypeParameter groupTypeParameter;
    private final String shortcut;

    public String getShortcut() {
        return this.shortcut;
    }

    @Override
    public String toString() {
        return "\nGroupType: " + this.name() + '\n'
                + "Parameter: " + this.groupTypeParameter + '\n';
    }
}
