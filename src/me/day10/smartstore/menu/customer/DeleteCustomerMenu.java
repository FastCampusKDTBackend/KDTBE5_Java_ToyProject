package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;
import me.day10.smartstore.menu.topic.CustomerMenu;

public class DeleteCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final DeleteCustomerMenu INSTANCE = new DeleteCustomerMenu();
    }
    private DeleteCustomerMenu() {}
    public static DeleteCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, CustomerMenu.getInstance());
    }
}
