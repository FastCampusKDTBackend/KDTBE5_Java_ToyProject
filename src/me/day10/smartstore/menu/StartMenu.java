package me.day10.smartstore.menu;

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

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
