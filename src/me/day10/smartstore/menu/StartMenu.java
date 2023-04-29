package me.day10.smartstore.menu;

public class StartMenu implements Menu {

    private static final StartMenu INSTANCE = new StartMenu();
    private StartMenu() {}
    public static StartMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
