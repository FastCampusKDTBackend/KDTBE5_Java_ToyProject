package me.smartstore.menu.group;

import me.smartstore.group.Group;

public class InputMinSpentHoursMenu extends InputGroupParmeterMenu {

    private static final String MIN_SPENT_HOURS_INPUT =
            '\n' + "Input " + "Minimum " + "Spent Hours" + ":\n" + END_INPUT;

    private static class InstanceHolder {
        private static final InputMinSpentHoursMenu INSTANCE = new InputMinSpentHoursMenu();
    }
    private InputMinSpentHoursMenu() { super(MIN_SPENT_HOURS_INPUT); }

    public static InputMinSpentHoursMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    protected void setTempProperty(Integer property) {
        Group.setTempMinSpentHours(property);
    }
}
