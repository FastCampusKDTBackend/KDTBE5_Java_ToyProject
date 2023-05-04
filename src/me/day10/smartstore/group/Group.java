package me.day10.smartstore.group;

import me.day10.smartstore.menu.exception.InvalidGroupNameException;

import java.util.Arrays;

public enum Group {

    GENERAL("G", new GroupParameter()),
    VIP("V", new GroupParameter()),
    VVIP("VV", new GroupParameter());

    Group(String shortcut, GroupParameter groupParameter) {
        this.shortcut = shortcut;
        this.groupParameter = groupParameter;
    }

    private final GroupParameter groupParameter;
    private final String shortcut;

    public static Group getGroupByString(String s) throws InvalidGroupNameException {
        return Arrays.stream(Group.values())
                .filter(group -> group.isName(s))
                .findAny()
                .orElseThrow(() -> new InvalidGroupNameException("\nInvalid Group Name for Input. Please try again.\n"));
    }

    private boolean isName(String s) {
        return (this.shortcut.equals(s) || this.name().equals(s));
    }

    public void setGroupParameter(Integer[] groupParameterArguments) {
        this.groupParameter.setMinSpentHours(groupParameterArguments[1]);
        this.groupParameter.setMinTotalAmountPaid(groupParameterArguments[2]);
    }

    @Override
    public String toString() {
        return "\nGroupType: " + this.name() + '\n'
                + "Parameter: " + this.groupParameter + '\n';
    }
}
