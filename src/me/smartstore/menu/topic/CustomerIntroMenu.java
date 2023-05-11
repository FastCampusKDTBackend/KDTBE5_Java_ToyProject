package me.smartstore.menu.topic;

import me.smartstore.menu.customer.DeleteCustomerMenu;
import me.smartstore.menu.customer.UpdateCustomerMenu;
import me.smartstore.menu.customer.ViewCustomerMenu;

public class CustomerIntroMenu extends TopicIntroMenu {

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
    private static final CustomerIntroMenu INSTANCE = new CustomerIntroMenu();

    private CustomerIntroMenu() { super(CUSTOMER_MENU_OUTPUT); }

    public static CustomerIntroMenu getInstance() { return INSTANCE; }

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
