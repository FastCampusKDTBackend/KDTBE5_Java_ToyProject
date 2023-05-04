package domain.menu;

import controller.MenuController;
import exception.ArrayEmptyException;
import exception.InputEndException;
import service.CustomerService;
import service.SummaryService;
import view.input.CustomerInput;

import java.util.Objects;

public class CustomerMenu implements Menu {

    private static final String[] MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static CustomerMenu customerMenu;
    private static CustomerService customerService;

    static {
        MENU_ITEMS = new String[]{"Add Customer Data", "View Customer Data",
                "Update Customer Data", "Delete Customer Data", "Back"};
        MENU_ITEMS_MAX_NUM = MENU_ITEMS.length;
        MENU_ITEMS_MIN_NUM = 1;
        customerService = CustomerService.getInstance();

    }

    public static CustomerMenu getInstance() {
        if (Objects.isNull(customerMenu)){
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {

    }

    @Override
    public String[] items() {
        return MENU_ITEMS;
    }

    @Override
    public int menuItemsMaxNum() {
        return MENU_ITEMS_MAX_NUM;
    }

    @Override
    public int menuItemsMinNum() {
        return MENU_ITEMS_MIN_NUM;
    }

    @Override
    public void service(int menuNum) {
        try {
            if (menuNum == 1) customerService.addCustomer(CustomerInput.inputCustomerNumber());
            if (menuNum == 2) customerService.viewCustomer();
            if (menuNum == 3) customerService.updateCustomer();
            if (menuNum == 4) customerService.deleteCustomer();
            SummaryService.getInstance().refreshClassifiedCustomers();
        } catch (InputEndException | ArrayEmptyException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
