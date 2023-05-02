package Menu;

public class CustomerMenu {

    private static CustomerMenu allCustomerMenu;

    public static CustomerMenu getInstance() {
        if (allCustomerMenu == null) {
            allCustomerMenu = new CustomerMenu();
        }
        return allCustomerMenu;
    }

    private CustomerMenu() {

    }
}

