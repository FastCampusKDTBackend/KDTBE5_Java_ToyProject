package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.CustomerMenu;

public class UpdateCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final UpdateCustomerMenu INSTANCE = new UpdateCustomerMenu();
    }
    private UpdateCustomerMenu() {}
    public static UpdateCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, CustomerMenu.getInstance());
    }
}
