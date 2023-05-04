package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class DeleteCustomerMenu extends Menu {

    private static final DeleteCustomerMenu INSTANCE = new DeleteCustomerMenu();
    private DeleteCustomerMenu() {}
    public static DeleteCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
