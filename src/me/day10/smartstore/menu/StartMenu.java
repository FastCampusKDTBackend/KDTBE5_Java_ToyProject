package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class StartMenu extends Menu {

    private static final StartMenu INSTANCE = new StartMenu(
            null,
            GroupMenu.getInstance(),                    // 1
            CustomerMenu.getInstance(),                 // 2
            ClassificationSummaryMenu.getInstance(),    // 3
            EndMenu.getInstance()                       // 4
    );
    private StartMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static StartMenu getInstance() { return INSTANCE; }

    private static final String OUTPUT =
                    '\n' +
                    "==============================" + '\n' +
                    "1. " + "Group" + '\n' +
                    "2. " + "Customer Data" + '\n' +
                    "3. " + "Classification Summary" + '\n' +
                    "4. " + "Quit" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            try {
                print(OUTPUT);
                int i = inputMenu();
                return nextMenu[i];
            } catch (InputMismatchException | InvalidMenuException e) {
                print(e.getMessage());
            }
        }
    }
}
