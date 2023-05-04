package me.day10.smartstore.menu;

public class EndMenu extends Menu {

    private static final EndMenu INSTANCE = new EndMenu();
    private EndMenu() {}
    public static EndMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Printer.getInstance().print("\nProgram Finished.\n");
        return null;
    }
}
