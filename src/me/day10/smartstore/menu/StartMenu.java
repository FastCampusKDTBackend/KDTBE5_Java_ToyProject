package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class StartMenu implements Menu {

    private static final StartMenu INSTANCE = new StartMenu();
    private StartMenu() {}
    public static StartMenu getInstance() { return INSTANCE; }

    private static final Reader reader = Reader.getInstance();
    private static final Printer printer = Printer.getInstance();
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
            } catch (InputMismatchException e) {
                print("Invalid Format for Input." + " Please try again.\n");
            } catch (Exception e) {
                print(e.getMessage());
            }
        }
    }

    private void print(String s) {
        printer.print(s);
    }

    private int input() throws InputMismatchException, Exception {
        int ret = reader.inputInteger();
        if (ret <= 0 || ret >= nextMenu.length)
            throw new Exception("Invalid Menu Input." + " Please try again.\n");
        return ret;
    }
}
