package me.smartstore.group;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.exception.InvalidGroupNameException;

import java.util.Arrays;

public enum Group {

    NONE("N"),
    GENERAL("G"),
    VIP("V"),
    VVIP("VV");

    private static final Group[] USED_GROUPS = { GENERAL, VIP, VVIP };
    private static GroupParameter tempParameter;
    private static Group tempGroup;

    private final String shortcut;
    private final GroupParameter groupParameter;

    Group(String shortcut) {
        this.shortcut = shortcut;
        this.groupParameter = new GroupParameter();
    }

    public static Group[] getUsedGroups() { return USED_GROUPS; }

    public static Group getGroupByString(String s) throws InvalidGroupNameException {
        Group[] groups = Group.getUsedGroups();
        return Arrays.stream(groups)
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
        Group[] groups = getUsedGroups();
        Group ret = NONE;
        int i = 0;
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

    public static void setTempMinSpentHours(Integer minSpentHours) {
        tempParameter.setMinSpentHours(minSpentHours);
    }

    public static void setTempMinTotalPaidAmount(Integer minTotalPaidAmount) {
        tempParameter.setMinTotalPaidAmount(minTotalPaidAmount);
    }

    public static void setTempParameter(Group group) {
        GroupParameter param = group.groupParameter;
        tempParameter = new GroupParameter(param.getMinSpentHours(), param.getMinTotalPaidAmount());
        tempGroup = group;
    }

    public static String getUpdateBeforeAndAfterInfo() {
        String before = tempGroup.groupParameter.toString();
        String after = tempParameter.toString();
        return getGroupNameInfo(tempGroup) +
                "Before: " + before + '\n'+
                "After : " + after;
    }

    public static void updateGroupParameter() {
        GroupParameter param = tempGroup.groupParameter;
        param.setMinSpentHours(tempParameter.getMinSpentHours());
        param.setMinTotalPaidAmount(tempParameter.getMinTotalPaidAmount());

        CustomerRepository.getInstance().updateGroupIn(tempGroup);
    }

    @Override
    public String toString() {
        return getGroupNameInfo(this)
                + "Parameter: " + this.groupParameter + '\n';
    }

    public static String getGroupNameInfo(Group group) {
        return '\n' + "Group: " + group.name() + '\n';
    }
}
