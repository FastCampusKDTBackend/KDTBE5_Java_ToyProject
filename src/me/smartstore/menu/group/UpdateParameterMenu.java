package me.smartstore.menu.group;

import me.smartstore.group.Group;
import me.smartstore.menu.exception.InputIsEndException;
import me.smartstore.menu.exception.InvalidGroupNameException;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.GroupMenu;

import java.util.InputMismatchException;

public class UpdateParameterMenu extends Menu {

    private static final String SET_GROUP_PARAMETER_OUTPUT =
                    '\n' +
                    "===== Set Group Parameter ====" + '\n' +
                    " 1. " + "Minimum " + "Spent Hours" + '\n' +
                    " 2. " + "Minimum " + "Total Paid Amount" + '\n' +
                    " 3. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";
    private static final String[] GROUP_PARAMETER_INPUT = {
            '\n' + "Input " + "Minimum " + "Spent Hours" + ":\n" + END_INPUT,
            '\n' + "Input " + "Minimum " + "Total Paid Amount" + ":\n" + END_INPUT
    };

    private static class InstanceHolder {
        private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    }

    private UpdateParameterMenu() { super(); }

    public static UpdateParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputStringOrEnd();
                Group group = Group.getGroupByString(groupName);
                print(group);
                inputGroupParameter(group);
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return getBackMenu();
            } catch (InvalidGroupNameException e) {
                print(e.getMessage());
            }
        }
    }

    @Override
    public void setNextMenus() {
        setNextMenus(
                null,                    // read minSpentHours
                null,                    // read minTotalPaidAmount
                GroupMenu.getInstance()  // Back => GroupMenu
        );
    }

    private void inputGroupParameter(Group group) {
        Integer[] groupParameterArguments = { null, null };
        while (true) {
            print(SET_GROUP_PARAMETER_OUTPUT);
            try {
                int menuIdx = inputMenuIdx();
                if (menuIdx <= 1) {
                    print(GROUP_PARAMETER_INPUT[menuIdx]);
                    groupParameterArguments[menuIdx] = inputZeroOrPositiveIntegerOrEnd();
                } else {
                    group.setGroupParameter(groupParameterArguments);
                    break;
                }
            } catch (InputIsEndException e) {
                print(e.getMessage());
                break;
            } catch (InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }
}
