package domain.menu.main.customer;

import domain.menu.Menu;

import java.util.Arrays;
import java.util.Optional;

public enum CustomerMenu implements Menu {
    ADD(1, "Add Customer Data", AddCustomerExecute.getMethod()),
    VIEW(2, "View Customer Data", ViewCustomerExecute.getMethod()),
    UPDATE(3, "Update Customer Data", UpdateCustomerExecute.getMethod()),
    DELETE(4, "Delete Customer Data", DeleteCustomerExecute.getMethod());

    private final int menuNumber;
    private final String menuName;
    private final Runnable runnable;

    CustomerMenu(int menuNumber, String menuName, Runnable runnable) {
        this.menuNumber = menuNumber;
        this.menuName = menuName;
        this.runnable = runnable;
    }

    public static Optional<CustomerMenu> findByNumber(int menuNumber) {
        return Arrays.stream(CustomerMenu.values())
                .filter(mainMenu -> mainMenu.menuNumber == menuNumber)
                .findFirst();
    }

    public static boolean isQuit(int menuNumber) {
        return CustomerMenu.values().length + 1 == menuNumber;
    }

    @Override
    public void execute() {
        runnable.run();
    }

    @Override
    public String toString() {
        return "  " + menuNumber + ". " + menuName + "\n";
    }
}
