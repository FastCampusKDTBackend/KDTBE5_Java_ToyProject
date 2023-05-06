package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

public class AddCustomerConfirmMenu extends Menu {

    private static class InstanceHolder {
        private static final AddCustomerConfirmMenu INSTANCE = new AddCustomerConfirmMenu();
    }
    private AddCustomerConfirmMenu() { super(); }
    public static AddCustomerConfirmMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }
}
