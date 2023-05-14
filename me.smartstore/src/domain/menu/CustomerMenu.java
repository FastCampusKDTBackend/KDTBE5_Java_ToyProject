package domain.menu;

import Service.CustomerService;
import handler.exception.InputEmptyException;
import handler.exception.InputEndException;

import java.util.NoSuchElementException;

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
            } catch (NoSuchElementException | InputEmptyException e) {
                //하위 메뉴에서 InputEmptyException 발생시 입력 중단하고 상위 메뉴로 이동
                System.out.println(e.getMessage());
            }
        }
    }
}