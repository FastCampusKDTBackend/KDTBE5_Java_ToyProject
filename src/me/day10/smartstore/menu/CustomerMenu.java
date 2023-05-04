package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class CustomerMenu extends Menu {

    private static final CustomerMenu INSTANCE = new CustomerMenu(
            null,
            AddCustomerMenu.getInstance(),
            ViewCustomerMenu.getInstance(),
            UpdateCustomerMenu.getInstance(),
            DeleteCustomerMenu.getInstance(),
            null    // back
    );
    private CustomerMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static CustomerMenu getInstance() { return INSTANCE; }

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

    @Override
    public Menu printAndInputAndGetNextMenu() {
        nextMenu[nextMenu.length - 1] = StartMenu.getInstance();
        while (true) {
            print(CUSTOMER_MENU_OUTPUT);
            try {
                int menu = inputMenu();
                return nextMenu[menu];
            } catch (InvalidMenuException | InputMismatchException e) {
                print(e.getMessage());
            }
        }
    }
}
