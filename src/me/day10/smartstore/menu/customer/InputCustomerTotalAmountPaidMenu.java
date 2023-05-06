package me.day10.smartstore.menu.customer;

import me.day10.smartstore.menu.Menu;

public class InputCustomerTotalAmountPaidMenu extends Menu {

    private static final String TOTAL_AMOUNT_PAID =
                    "Input " + "Customer" + "'s " + "Total Amount Paid" + ":\n" +
                    END_INPUT;
    private static final InputCustomerTotalAmountPaidMenu INSTANCE = new InputCustomerTotalAmountPaidMenu(null, null);
    private InputCustomerTotalAmountPaidMenu(Menu... nextMenus) { super(nextMenus); }
    public static InputCustomerTotalAmountPaidMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() { return null; }
}
