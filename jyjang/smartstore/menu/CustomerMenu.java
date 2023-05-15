package jyjang.smartstore.menu;

public class CustomerMenu implements Menu {

    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null)
            customerMenu = new CustomerMenu();
        return customerMenu;
    }

    private CustomerMenu() {
        super();

    }

    @Override
    public void manage() {
        while (true) {
            int choice = displayMenu(new String[] {
                    "Add Customer"
                    , "View Customer"
                    , "Update Customer"
                    , "Delete Customer"
                    , "Back"});
        }

    }
}
