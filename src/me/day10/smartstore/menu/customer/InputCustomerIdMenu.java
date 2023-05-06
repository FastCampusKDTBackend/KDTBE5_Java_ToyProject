package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

import static me.day10.smartstore.customer.Customer.ID_FORMAT;

public class InputCustomerIdMenu extends Menu {

    private static final String ID_INPUT =
                    "Input " + "Customer" + "'s " + "ID" + ":\n" +
                    ID_FORMAT + '\n' +
                    END_INPUT;
    private static final InputCustomerIdMenu INSTANCE = new InputCustomerIdMenu(null, null);
    private InputCustomerIdMenu(Menu... nextMenus) { super(nextMenus); }
    public static InputCustomerIdMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }
}
