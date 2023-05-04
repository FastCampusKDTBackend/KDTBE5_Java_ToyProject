package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class UpdateCustomerMenu extends Menu {

    private static final UpdateCustomerMenu INSTANCE = new UpdateCustomerMenu();
    private UpdateCustomerMenu() {}
    public static UpdateCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
