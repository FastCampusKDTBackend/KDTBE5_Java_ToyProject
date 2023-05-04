package me.day10.smartstore.menu;

public class UpdateParameterMenu extends Menu {

    private static class InstanceHolder {
        private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    }
    private UpdateParameterMenu() {}
    public static UpdateParameterMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
