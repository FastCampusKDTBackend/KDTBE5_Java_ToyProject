package me.day10.smartstore.menu;

import me.day10.smartstore.group.Group;

import java.util.InputMismatchException;

public class SetParameterMenu extends Menu {

    private static final String SET_GROUP_PARAMETER_OUTPUT =
            "===== Set Group Parameter ====" + '\n' +
                    " 1. " + "Minimum Spent Hours" + '\n' +
                    " 2. " + "Minimum Total Amount Paid" + '\n' +
                    " 3. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static final String[] GROUP_PARAMETER_INPUT = {
            null,
            "\n" + "Input " + "Minimum Spent Hours" + ":\n" + END_INPUT,
            "\n" + "Input " + "Minimum Total Amount Paid" + ":\n" + END_INPUT
    };

    // null means handle the next menus in this
    private static final SetParameterMenu INSTANCE = new SetParameterMenu(
            null,
            null, // read minSpentHours
            null, // read minTotalAmountPaid
            null  // Back => GroupMenu
    );
    private SetParameterMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static SetParameterMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Menu backMenu = GroupMenu.getInstance();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputGroupName();
                Group group = Group.getGroupByString(groupName);
                print(group);
                inputGroupParameter(group);
            } catch (InputIsEndException e) {
                print(e.getMessage());
                return backMenu;
            } catch (InvalidGroupNameException e) {
                print(e.getMessage());
            }
        }
    }

    private void inputGroupParameter(Group group) {
        Integer[] groupParameterArguments = { null, null, null }; // null, .., ..
        while (true) {
            print(SET_GROUP_PARAMETER_OUTPUT);
            try {
                int menu = inputMenu();
                if (menu <= 2) {
                    assert menu != 0;
                    print(GROUP_PARAMETER_INPUT[menu]);
                    groupParameterArguments[menu] = inputIntegerOrEnd();
                } else {
                    group.setGroupParameter(groupParameterArguments);
                    break;
                }
            } catch (InputIsEndException e) {
                print(e.getMessage());
                break;
            } catch (InvalidMenuException | InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    private Integer inputIntegerOrEnd() throws InputIsEndException {
        String s = reader.inputString().toUpperCase();
        checkIfInputIsEnd(s);

        try {
            int i = Integer.parseInt(s);
            if (i < 0)
                throw new InputMismatchException("Invalid Input. Please try again.\n");
            return i;
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Invalid Format for Input. Please try again.\n");
        }
    }
}
