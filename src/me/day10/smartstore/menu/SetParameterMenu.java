package me.day10.smartstore.menu;

public class SetParameterMenu implements Menu {

    private static class InstanceHolder {
        private static final SetParameterMenu INSTANCE = new SetParameterMenu();
    }
    private SetParameterMenu() {}
    public static SetParameterMenu getInstance() { return SetParameterMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
