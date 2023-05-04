package me.day10.smartstore.menu;

import me.day10.smartstore.group.GroupType;

import java.util.InputMismatchException;

public class SetParameterMenu extends Menu {

    private static final SetParameterMenu INSTANCE = new SetParameterMenu(
            null,
            null, // read minSpentTime
            null, // read minTotalPaid
            null  // Back => GroupMenu
    );
    private SetParameterMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static SetParameterMenu getInstance() { return INSTANCE; }

    private static final String GROUP_SET_PARAMETER_OUTPUT =
            "===== Set Group Parameter ====\n" +
            " 1. Minimum Spent Time\n" +
            " 2. Minimum Total Paid\n" +
            " 3. Back\n" +
            "==============================\n" +
            "Choose One: ";

    private static final String[] GROUP_PARAMETER_INPUT = {
            null,
            "\n" + "Input Minimum " + "Spent Time:\n" + END_INPUT,
            "\n" + "Input Minimum " + "Total Paid:\n" + END_INPUT
    };

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Menu backMenu = GroupMenu.getInstance();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputGroupName();
                GroupType groupType = GroupType.getGroupTypeByString(groupName);
                print(groupType);
                inputGroupParameter(groupType);
            } catch (BackMenuException e) {
                print(e.getMessage());
                return backMenu;
            } catch (InvalidGroupTypeException e) {
                print(e.getMessage());
            }
        }
    }

    private void inputGroupParameter(GroupType groupType) {
        Integer[] groupParameterArguments = { null, null, null }; // null, minSpentTime, minTotalPaid
        while (true) {
            print(GROUP_SET_PARAMETER_OUTPUT);
            try {
                int menu = inputMenu();
                if (menu <= 2) {
                    print(GROUP_PARAMETER_INPUT[menu]);
                    groupParameterArguments[menu] = inputIntegerOrEnd();
                } else {
                    groupType.setGroupTypeParameter(groupParameterArguments);
                    break;
                }
            } catch (BackMenuException e) {
                print(e.getMessage());
                break;
            } catch (InvalidMenuException | InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }

    private Integer inputIntegerOrEnd() throws BackMenuException {
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
