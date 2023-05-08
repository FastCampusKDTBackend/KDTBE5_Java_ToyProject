package me.smartstore.menu.group;

import me.smartstore.menu.Menu;

public class UpdateParameterMenu extends Menu {

    private static final UpdateParameterMenu INSTANCE = new UpdateParameterMenu();
    private UpdateParameterMenu() {}
    public static UpdateParameterMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }

    @Override
    protected void setNextMenus() {}
}
