package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

import static me.day10.smartstore.customer.Customer.ID_FORMAT;

public class InputCustomerIdMenu extends Menu {

    private static final String ID_INPUT =
                    "Input " + "Customer" + "'s " + "ID" + ":\n" +
                    ID_FORMAT + '\n' +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerIdMenu INSTANCE = new InputCustomerIdMenu();
    }
    private InputCustomerIdMenu() { super(); }
    public static InputCustomerIdMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }
}
