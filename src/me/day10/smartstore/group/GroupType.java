package me.day10.smartstore.group;

import me.day10.smartstore.menu.InvalidGroupTypeException;

import java.util.Arrays;

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

    public static GroupType getGroupTypeByString(String s) throws InvalidGroupTypeException {
        return Arrays.stream(GroupType.values())
                .filter(groupType -> groupType.isName(s))
                .findAny()
                .orElseThrow(() -> new InvalidGroupTypeException("\nInvalid Group Name for Input. Please try again.\n"));
    }

    private boolean isName(String s) {
        return (this.shortcut.equals(s) || this.name().equals(s));
    }

    @Override
    public String toString() {
        return "\nGroupType: " + this.name() + '\n'
                + "Parameter: " + this.groupTypeParameter + '\n';
    }
}
