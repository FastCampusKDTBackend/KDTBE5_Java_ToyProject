package smartstore.menu;

public class CustomerMenu implements Menu {
    // singleton
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
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Add Customer",
                    "View Customer",
                    "Update Customer",
                    "Delete Customer",
                    "Back"});
            if(choice == 1) addCustomer();
            else if(choice == 2) viewCustomer();
            else if(choice == 3) updateCustomer();
            else if(choice == 4) deleteCustomer();
            else break;
        }
    }

    private void addCustomer() {
    }

    private void viewCustomer() {
        
    }

    private void updateCustomer() {
        
    }

    private void deleteCustomer() {
        
    }
}
