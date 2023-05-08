package me.day10.smartstore.menu.topic;

import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.customer.*;

public class UpdateCustomerSelectPropertyMenu extends TopicIntroMenu {

    private static final String CUSTOMER_PROPERTY_MENU_OUTPUT =
                    '\n' +
                    "===== Property to update =====" + '\n' +
                    " 1. " + "Customer " + "Name" + '\n' +
                    " 2. " + "Customer " + "Spent Hours" + '\n' +
                    " 3. " + "Customer " + "Total Amount Paid" + '\n' +
                    " 4. " + "Confirm" + '\n' +
                    " 5. " + "Cancel(Back)" + '\n' +
                    "==============================" + '\n' +
                    "Choose One: ";

    private static class InstanceHolder {
        private static final UpdateCustomerSelectPropertyMenu INSTANCE = new UpdateCustomerSelectPropertyMenu();
    }

    private UpdateCustomerSelectPropertyMenu() { super(CUSTOMER_PROPERTY_MENU_OUTPUT); }

    public static UpdateCustomerSelectPropertyMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {
        Menu[] nextMenus = {
                InputCustomerNameMenu.getInstance(),            // name
                InputCustomerSpentHoursMenu.getInstance(),      // spent hours
                InputCustomerTotalAmountPaidMenu.getInstance(), // total amount paid
                UpdateCustomerConfirmMenu.getInstance(),        // confirm
                CustomerMenu.getInstance()                      // cancel(back) => CustomerMenu
        };
        for (int i = 0; i <= 2; ++i)
            nextMenus[i].setNextMenus(null, this);
        setNextMenus(nextMenus);
    }
}
