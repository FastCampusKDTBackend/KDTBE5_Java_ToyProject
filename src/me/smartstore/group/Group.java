package me.smartstore.group;

import me.smartstore.menu.exception.InvalidGroupNameException;

import java.util.Arrays;

public enum Group {

    NONE("N"),
    GENERAL("G"),
    VIP("V"),
    VVIP("VV");

    private final String shortcut;
    private final GroupParameter groupParameter;

    Group(String shortcut) {
        this.shortcut = shortcut;
        this.groupParameter = new GroupParameter();
    }

    public static Group getGroupByString(String s) throws InvalidGroupNameException {
        Group[] groups = Group.values();
        return Arrays.stream(groups, 1, groups.length)
                .filter(group -> group.isName(s))
                .findAny()
                .orElseThrow(() -> new InvalidGroupNameException("\nInvalid Group Name for Input. Please try again.\n"));
    }

    private boolean isName(String s) {
        return this.shortcut.equalsIgnoreCase(s) || this.name().equalsIgnoreCase(s);
    }

    public void setGroupParameter(Integer[] groupParameterArguments) {
        this.groupParameter.setMinSpentHours(groupParameterArguments[0]);
        this.groupParameter.setMinTotalAmountPaid(groupParameterArguments[1]);
    }

    @Override
    public String toString() {
        return "\nGroupType: " + this.name() + '\n'
                + "Parameter: " + this.groupParameter + '\n';
    }
}
