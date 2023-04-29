package me.day10.smartstore.menu;

public class GroupMenu implements Menu {

    private static class InstanceHolder {
        private static final GroupMenu INSTANCE = new GroupMenu();
    }
    private GroupMenu() {}
    public static GroupMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
