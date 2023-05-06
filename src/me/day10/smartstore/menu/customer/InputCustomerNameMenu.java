package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class InputCustomerNameMenu extends Menu {

    private static final String NAME_INPUT =
                    "Input " + "Customer" + "'s " + "Name" + ":\n" +
                    END_INPUT;
    private static final InputCustomerNameMenu INSTANCE = new InputCustomerNameMenu(null, null);
    private InputCustomerNameMenu(Menu... nextMenus) { super(nextMenus); }
    public static InputCustomerNameMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }
}
