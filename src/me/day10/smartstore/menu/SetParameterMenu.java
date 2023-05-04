package me.day10.smartstore.menu;

import me.day10.smartstore.group.GroupType;

import java.util.InputMismatchException;

public class SetParameterMenu extends Menu {

    private static class InstanceHolder {
        private static final SetParameterMenu INSTANCE = new SetParameterMenu(
                null,
                null, // read minSpentTime
                null, // read minTotalPaid
                null  // Back => GroupMenu
        );
    }
    private SetParameterMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static SetParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    private static final String END_INPUT = "** Press 'end', if you want to exit! **\n";
    private static final String GROUP_OUTPUT =
            '\n' +
            "Which group (GENERAL (G), VIP (V), VVIP (VV))?\n"
            + END_INPUT;

    private static final String GROUP_SET_PARAMETER_OUTPUT =
            "===== Set Group Parameter ====\n" +
            " 1. Minimum Spent Time\n" +
            " 2. Minimum Total Paid\n" +
            " 3. Back\n" +
            "==============================\n" +
            "Choose One: ";

    private static final String MIN_SPENT_TIME_INPUT =
            "\n" +
            "Input Minimum Spent Time: \n" +
            END_INPUT;

    private static final String MIN_TOTAL_PAID_INPUT =
            "\n" +
            "Input Minimum Total Paid: \n" +
            END_INPUT;

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Menu backMenu = GroupMenu.getInstance();
        while (true) {
            print(GROUP_OUTPUT);
            try {
                String groupName = inputGroupName();
                GroupType groupType = GroupType.getGroupTypeByString(groupName);
                print(groupType);

                Integer minSpentTime = null;
                Integer minTotalPaid = null;
                while (true) {
                    print(GROUP_SET_PARAMETER_OUTPUT);
                    try {
                        int menu = inputMenu();

                        if (menu == 1) {
                            print(MIN_SPENT_TIME_INPUT);
                            minSpentTime = inputIntegerOrEnd();
                        } else if (menu == 2) {
                            print(MIN_TOTAL_PAID_INPUT);
                            minTotalPaid = inputIntegerOrEnd();
                        } else {
                            groupType.setGroupTypeParameter(minSpentTime, minTotalPaid);
                            break;
                        }
                    } catch (InvalidMenuException | InputMismatchException e) {
                        print(e.getMessage());
                    } catch (BackMenuException e) {
                        print(e.getMessage());
                        break;
                    }
                }
            } catch (InvalidGroupTypeException e) {
                print(e.getMessage());
            } catch (BackMenuException e) {
                return backMenu;
            }
        }
    }

    private String inputGroupName() throws BackMenuException {
        String s = reader.inputString().toUpperCase();
        checkIfInputIsEnd(s);
        return s;
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
