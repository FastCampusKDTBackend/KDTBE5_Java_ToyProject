package me.day10.smartstore.menu;

public class EndMenu implements Menu {

    private static class InstanceHolder {
        private static final EndMenu INSTANCE = new EndMenu();
    }
    private EndMenu() {}
    public static EndMenu getInstance() { return EndMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
