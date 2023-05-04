package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class AddCustomerMenu extends Menu {

    private static final AddCustomerMenu INSTANCE = new AddCustomerMenu();
    private AddCustomerMenu() {}
    public static AddCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
