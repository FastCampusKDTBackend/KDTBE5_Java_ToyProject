package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class AddCustomerConfirmMenu extends Menu {

    private static final AddCustomerConfirmMenu INSTANCE = new AddCustomerConfirmMenu(null, null);
    private AddCustomerConfirmMenu(Menu... nextMenus) { super(nextMenus); }
    public static AddCustomerConfirmMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }
}
