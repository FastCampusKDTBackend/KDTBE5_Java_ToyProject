package me.day10.smartstore.menu;

public class CustomerMenu extends TopicIntroMenu {

    private static final String CUSTOMER_MENU_OUTPUT =
            '\n' +
                    "======= " + "Customer" + " Menu ========\n" +
                    " 1. Add " + "Customer" + '\n' +
                    " 2. View " + "Customer" + '\n' +
                    " 3. Update " + "Customer" + '\n' +
                    " 4. Delete " + "Customer" + '\n' +
                    " 5. Back\n" +
                    "==============================\n" +
                    "Choose One: ";

    private static final CustomerMenu INSTANCE = new CustomerMenu(
            CUSTOMER_MENU_OUTPUT,
            null,
            AddCustomerMenu.getInstance(),
            ViewCustomerMenu.getInstance(),
            UpdateCustomerMenu.getInstance(),
            DeleteCustomerMenu.getInstance(),
            null    // back
    );

    private CustomerMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static CustomerMenu getInstance() { return INSTANCE; }
}
