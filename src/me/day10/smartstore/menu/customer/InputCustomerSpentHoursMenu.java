package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

public class InputCustomerSpentHoursMenu extends Menu {

    private static final String SPENT_HOURS_INPUT =
                    "Input " + "Customer" + "'s " + "Spent Hours" + ":\n" +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerSpentHoursMenu INSTANCE = new InputCustomerSpentHoursMenu();
    }
    private InputCustomerSpentHoursMenu() { super(); }
    public static InputCustomerSpentHoursMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }
}
