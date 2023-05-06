package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.AddCustomerMenu;

public class InputCustomerTotalAmountPaidMenu extends Menu {

    private static final String TOTAL_AMOUNT_PAID =
                    "Input " + "Customer" + "'s " + "Total Amount Paid" + ":\n" +
                    END_INPUT;

    private static class InstanceHolder {
        private static final InputCustomerTotalAmountPaidMenu INSTANCE = new InputCustomerTotalAmountPaidMenu();
    }
    private InputCustomerTotalAmountPaidMenu() { super(); }
    public static InputCustomerTotalAmountPaidMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, AddCustomerMenu.getInstance());
    }
}
