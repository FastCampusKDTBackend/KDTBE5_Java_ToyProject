package me.day10.smartstore.menu.topic;

import me.day10.smartstore.menu.customer.DeleteCustomerMenu;
import me.day10.smartstore.menu.customer.UpdateCustomerMenu;
import me.day10.smartstore.menu.customer.ViewCustomerMenu;

public class CustomerMenu extends TopicIntroMenu {

    private static final String CUSTOMER_MENU_OUTPUT =
                    '\n' +
                    "======= " + "Customer" + " Menu ========\n" +
                    " 1. " + "Add " + "Customer" + '\n' +
                    " 2. " + "View " + "Customer" + '\n' +
                    " 3. " + "Update " + "Customer" + '\n' +
                    " 4. " + "Delete " + "Customer" + '\n' +
                    " 5. " + "Back" + '\n' +
                    "==============================\n" +
                    "Choose One: ";
    private static final CustomerMenu INSTANCE = new CustomerMenu();

    private CustomerMenu() { super(CUSTOMER_MENU_OUTPUT); }

    public static CustomerMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(
                AddCustomerMenu.getInstance(),
                ViewCustomerMenu.getInstance(),
                UpdateCustomerMenu.getInstance(),
                DeleteCustomerMenu.getInstance(),
                StartMenu.getInstance()
        );
    }
}
