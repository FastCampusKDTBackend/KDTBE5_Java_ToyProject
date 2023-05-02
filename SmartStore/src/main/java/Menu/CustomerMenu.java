package Menu;

public class CustomerMenu implements Menu {

    private static CustomerMenu allCustomerMenu;

    public static CustomerMenu getInstance() {
        if (allCustomerMenu == null) {
            allCustomerMenu = new CustomerMenu();
        }
        return allCustomerMenu;
    }

    private CustomerMenu() {

    }

    @Override
    public void manage() {

    }
}

