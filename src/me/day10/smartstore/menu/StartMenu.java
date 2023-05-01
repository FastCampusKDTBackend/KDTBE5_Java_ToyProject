package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class StartMenu implements Menu {

    private static final StartMenu INSTANCE = new StartMenu();
    private StartMenu() {}
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

    private final Menu[] nextMenu = {
            null,
            GroupMenu.getInstance(),                    // 1
            CustomerMenu.getInstance(),                 // 2
            ClassificationSummaryMenu.getInstance(),    // 3
            EndMenu.getInstance()                       // 4
    };

    @Override
    public Menu printAndInputAndGetNextMenu() {
        while (true) {
            try {
                print(OUTPUT);
                int i = input();
                return nextMenu[i];
            } catch (Exception e) {
                // something to do
            }
        }
    }

    private void print(String s) {}

    private int input() { return 0; }
}
