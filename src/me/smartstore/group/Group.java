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
        this.groupParameter.setMinTotalPaidAmount(groupParameterArguments[1]);
    }

    public static Group calculate(Integer spentHours, Integer totalPaidAmount) {
        Group[] groups = values();
        Group ret = NONE;
        int i = 1;
        for (; i < groups.length; ++i) {
            Group group = groups[i];
            GroupParameter parameter = group.groupParameter;
            Integer minSpentHours = parameter.getMinSpentHours();
            Integer minTotalPaidAmount = parameter.getMinTotalPaidAmount();
            if (minSpentHours == null && minTotalPaidAmount == null)
                continue;
            if (minSpentHours != null)
                if (spentHours == null || spentHours < minSpentHours) break;
            if (minTotalPaidAmount != null)
                if (totalPaidAmount == null || totalPaidAmount < minTotalPaidAmount) break;
            ret = group;
        }
        return ret;
    }

    @Override
    public String toString() {
        return "\nGroupType: " + this.name() + '\n'
                + "Parameter: " + this.groupParameter + '\n';
    }
}
