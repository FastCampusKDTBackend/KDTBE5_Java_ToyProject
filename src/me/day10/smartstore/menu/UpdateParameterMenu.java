package me.day10.smartstore.menu;

public class UpdateParameterMenu implements Menu {

    private static class InstanceHolder {
        private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    }
    private UpdateParameterMenu() {}
    public static UpdateParameterMenu getInstance() { return UpdateParameterMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
