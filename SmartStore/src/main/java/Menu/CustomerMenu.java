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
        while (true) {
            int choice = allCustomerMenu.chooseMenu(new String[] {
                    "Add Customer Data",
                    "View Customer Data",
                    "Update Customer Data",
                    "Delete Customer Data",
                    "Back"
            });
            
            if (choice == 1) addCustomerData();
            else if (choice == 2) viewCustomerData();
            else if (choice == 3) updateCustomerData();
            else if (choice == 4) deleteCustomerData();
            else if (choice == 5) break;
        }
    }

    private void addCustomerData() {
        System.out.println("addCustomerData");
    }

    private void viewCustomerData() {
        System.out.println("viewCustomerData");
    }

    private void updateCustomerData() {
        System.out.println("updateCustomerData");
    }

    private void deleteCustomerData() {
        System.out.println("deleteCustomerData");
    }
}

