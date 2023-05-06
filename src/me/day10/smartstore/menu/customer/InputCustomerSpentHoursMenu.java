package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class InputCustomerSpentHoursMenu extends Menu {

    private static final String SPENT_HOURS_INPUT =
                    "Input " + "Customer" + "'s " + "Spent Hours" + ":\n" +
                    END_INPUT;
    private static final InputCustomerSpentHoursMenu INSTANCE = new InputCustomerSpentHoursMenu(null, null);
    private InputCustomerSpentHoursMenu(Menu... nextMenus) { super(nextMenus); }
    public static InputCustomerSpentHoursMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }
}
