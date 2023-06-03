package domain.menu;

import Service.CustomerService;
import handler.exception.InputFormatException;
import handler.exception.InputRangeException;

public class CustomerMenu implements Menu {

    private static final CustomerService customerService = CustomerService.getInstance();
    private static CustomerMenu customerMenu;

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {}

    @Override
    public void manage() {
        while (true) {
            try {
                int choice = customerMenu.selectMenu(new String[]{
                        "Add Customer Data",
                        "View Customer Data",
                        "Update Customer Data",
                        "Delete Customer Data",
                        "Back"
                });

                if (choice == 1) customerService.addCustomer();
                else if (choice == 2) customerService.showCustomerAll();
                else if (choice == 3) customerService.updateCustomer();
                else if (choice == 4) customerService.deleteCustomer();
                else if (choice == 5) break;
            } catch (InputRangeException | InputFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}