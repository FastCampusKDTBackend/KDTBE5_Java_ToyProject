package me.day10.smartstore.menu;

public class UpdateParameterMenu extends Menu {

    private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    private UpdateParameterMenu() {}
    public static UpdateParameterMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
