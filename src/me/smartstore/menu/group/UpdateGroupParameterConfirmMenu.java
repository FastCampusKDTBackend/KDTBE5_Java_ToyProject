package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.GroupIntroMenu;

import java.util.InputMismatchException;

public class UpdateGroupParameterConfirmMenu extends Menu {

    private static final String UPDATE_CUSTOMER_CONFIRM_OUTPUT =
                    '\n' +
                    "= Update Parameter Confirm ===" + '\n' +
                    " 1. " + "Yes (Update)" + '\n' +
                    " 2. " + "No (Back)" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static class InstanceHolder {
        private static final UpdateGroupParameterConfirmMenu INSTANCE = new UpdateGroupParameterConfirmMenu();
    }
    private UpdateGroupParameterConfirmMenu() { super(); }
    public static UpdateGroupParameterConfirmMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        final String updateBeforeAndAfterInfo = Group.getUpdateBeforeAndAfterInfo();
        while (true) {
            try {
                print(updateBeforeAndAfterInfo);
                print(UPDATE_CUSTOMER_CONFIRM_OUTPUT);
                int menuIdx = inputMenuIdx();
                if (menuIdx == 0)
                    Group.updateGroupParameter();
                return getNextMenu(menuIdx);
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(GroupIntroMenu.getInstance(), UpdateParameterMenu.getInstance());
    }
}
