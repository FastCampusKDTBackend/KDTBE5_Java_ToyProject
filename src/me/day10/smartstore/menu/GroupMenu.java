package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class GroupMenu extends Menu {

    private static class InstanceHolder {
        private static final GroupMenu INSTANCE = new GroupMenu(
                null,
                SetParameterMenu.getInstance(),     // 1
                ViewParameterMenu.getInstance(),    // 2
                UpdateParameterMenu.getInstance(),  // 3
                null                                // 4 (StartMenu 순환 의존)
        );
    }
    private GroupMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static GroupMenu getInstance() { return InstanceHolder.INSTANCE; }

    private static final String MENU_OUTPUT =
                    '\n' +
                    "==========Group Menu==========" + '\n' +
                    "1. " + "Set Parameter" + '\n' +
                    "2. " + "View Parameter" + '\n' +
                    "3. " + "Update Parameter" + '\n' +
                    "4. " + "Back" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            try {
                print(MENU_OUTPUT);
                int menu = inputMenu();
                return (menu == 4) ? StartMenu.getInstance() : nextMenu[menu];
            } catch (InputMismatchException | InvalidMenuException e) {
                print(e.getMessage());
            }
        }
    }
}
