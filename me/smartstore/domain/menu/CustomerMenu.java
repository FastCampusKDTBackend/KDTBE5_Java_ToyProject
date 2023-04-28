package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customers;
public class CustomerMenu implements Menu {
    private static CustomerMenu customerMenu;
    private final Customers allCustomers;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {
        this.allCustomers = Customers.getInstance();
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                "Add Customer Data",
                "View Customer Data",
                "Update Customer Data",
                "Delete Customer Data",
                "Back"
            });

            if (choice == 1) {
                createCustomer();
            } else if (choice == 2) {
                findAllCustomers();
            } else if (choice == 3) {
                updateCustomer();
            } else if (choice == 4) {
                deleteCustomer();
            } else {
                break;
            }
        }
    }

    // TODO: Customer Methods
    private void createCustomer() {}

    private void findAllCustomers() {}

    private void updateCustomer() {}

    private void deleteCustomer() {}
}
