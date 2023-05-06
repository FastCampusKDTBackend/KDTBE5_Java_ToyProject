package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

public class InputCustomerNameMenu extends Menu {

    private static final String NAME_INPUT =
                    "Input " + "Customer" + "'s " + "Name" + ":\n" +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerNameMenu INSTANCE = new InputCustomerNameMenu();
    }
    private InputCustomerNameMenu() { super(); }
    public static InputCustomerNameMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }
}
