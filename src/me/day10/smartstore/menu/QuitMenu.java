package me.day10.smartstore.menu;

public class QuitMenu extends Menu {

    private static final QuitMenu INSTANCE = new QuitMenu();
    private QuitMenu() {}
    public static QuitMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        Printer.getInstance().print("\nProgram Finished.\n");
        return null;
    }
}
