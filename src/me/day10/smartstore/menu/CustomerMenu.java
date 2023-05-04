package me.day10.smartstore.menu;

import java.util.InputMismatchException;

public class CustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final CustomerMenu INSTANCE = new CustomerMenu();
    }
    private CustomerMenu() {}
    public static CustomerMenu getInstance() { return CustomerMenu.InstanceHolder.INSTANCE; }

    private static final Reader reader = Reader.getInstance();
    private static final Printer printer = Printer.getInstance();
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

    private final Menu nextMenu[] = {
            null,
            AddCustomerMenu.getInstance(),
            ViewCustomerMenu.getInstance(),
            UpdateCustomerMenu.getInstance(),
            DeleteCustomerMenu.getInstance(),
            null    // back
    };

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

    private void print(Object s) {
        printer.print(s.toString());
    }

    private int inputMenu() throws InvalidMenuException, InputMismatchException {
        int i = reader.inputInteger();
        if (i <= 0 || i >= nextMenu.length)
            throw new InvalidMenuException("Invalid Menu Input." + " Please try again.\n");
        return i;
    }
}
