package me.smartstore.menu.group;

import me.smartstore.group.Group;

public class InputMinTotalPaidAmountMenu extends InputGroupParmeterMenu {

    private static final String MIN_SPENT_HOURS_INPUT =
            '\n' + "Input " + "Minimum " + "Total Paid Amount" + ":\n" + END_INPUT;

    private static class InstanceHolder {
        private static final InputMinTotalPaidAmountMenu INSTANCE = new InputMinTotalPaidAmountMenu();
    }
    private InputMinTotalPaidAmountMenu() { super(MIN_SPENT_HOURS_INPUT); }

    public static InputMinTotalPaidAmountMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    protected void setTempProperty(Integer property) {
        Group.setTempMinTotalPaidAmount(property);
    }
}
