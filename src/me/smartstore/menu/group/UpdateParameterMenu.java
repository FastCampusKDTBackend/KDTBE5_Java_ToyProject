package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.GroupIntroMenu;

import java.util.InputMismatchException;

public class UpdateParameterMenu extends GroupParameterMenu {

    private static final String UPDATE_PARAMETER_INPUT =
                    '\n' +
                    "======= Update Parameter =====" + '\n' +
                    " 1. " + "Minimum " + "Spent Hours" + '\n' +
                    " 2. " + "Minimum " + "Total Paid Amount" + '\n' +
                    " 3. " + "Confirm" + '\n' +
                    " 4. " + "Cancel(Back)" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static class InstanceHolder {
        private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    }

    private UpdateParameterMenu() {}

    public static UpdateParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public void setNextMenus() {
        setNextMenus(
                InputMinSpentHoursMenu.getInstance(),
                InputMinTotalPaidAmountMenu.getInstance(),
                UpdateGroupParameterConfirmMenu.getInstance(),
                GroupIntroMenu.getInstance()
        );
    }

    @Override
    protected Menu handleAndMoveToNextMenu(Group group) {
        if (getPrevMenu() == GroupIntroMenu.getInstance()) {
            setNextMenus();
            print(group);
            Group.setTempParameter(group);
        }
        while (true) {
            try {
                print(UPDATE_PARAMETER_INPUT);
                int menuIdx = inputMenuIdx();
                return getNextMenu(menuIdx);
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }
}
