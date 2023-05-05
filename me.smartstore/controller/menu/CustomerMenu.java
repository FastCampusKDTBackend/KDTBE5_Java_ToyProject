package controller.menu;

import domain.customer.Customer;
import exception.ArrayEmptyException;
import exception.InputEndException;
import exception.InputFormatException;
import exception.InputRangeException;
import service.CustomerService;
import service.SummaryService;
import view.Input;
import view.Output;

import java.util.Objects;

public class CustomerMenu implements Menu {

    private static final String[] CUSTOMER_MAIN_MENU_ITEMS;
    private static final String[] CUSTOMER_SET_MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static CustomerService customerService;

    private static CustomerMenu customerMenu;

    static {
        CUSTOMER_MAIN_MENU_ITEMS = new String[]{"Add Customer Data", "View Customer Data",
                "Update Customer Data", "Delete Customer Data", "Back"};

        CUSTOMER_SET_MENU_ITEMS = new String[]{"Customer Name", "Customer ID",
                "Customer Store Usage Time", "Customer Total Payment Amount", "Back"};

        MENU_ITEMS_MAX_NUM = CUSTOMER_MAIN_MENU_ITEMS.length;
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
        return CUSTOMER_MAIN_MENU_ITEMS;
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
            if (menuNum == 1) addCustomer();
            if (menuNum == 2) viewCustomer();
            if (menuNum == 3) updateCustomer();
            if (menuNum == 4) deleteCustomer();

        } catch (InputEndException | ArrayEmptyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Customer selectCustomer(){
        return customerService.selectCustomerByNumber(Input.inputTargetCustomerNumber());
    }

    /* Add Customer */
    private void addCustomer() {
        int inputCustomerNumber = Input.inputCustomerNumber();

        for (int i = 1; i <= inputCustomerNumber; i++) {
            System.out.printf("\n====== Customer %d Info. ======\n", i);
            Customer customer = new Customer();
            setCustomer(customer);
            customerService.insertCustomer(customer);
        }
        callSummaryServiceRefresh();
    }

    /* Update Customer */
    private void updateCustomer(){
        viewCustomer();
        setCustomer(selectCustomer());
        callSummaryServiceRefresh();
    }

    /* View All Customers */
    private void viewCustomer(){
        Output.customerList(customerService.selectAllCustomer());
    }

    /* Delete Customer */
    private void deleteCustomer() {
        viewCustomer();
        customerService.deleteCustomerByNumber(Input.inputTargetCustomerNumber());
        callSummaryServiceRefresh();
    }

    /* CustomerMenu in SubMenu for SetCustomer */
    private void setCustomer(Customer customer) {
        while (true) {
            try {
                int choiceMenuNumber = chooseSetCustomerMenu();
                if (choiceMenuNumber == 5) break;
                if (choiceMenuNumber == 1) customer.setName(Input.inputCustomerName());
                if (choiceMenuNumber == 2) customer.setId(Input.inputCustomerId());
                if (choiceMenuNumber == 3) customer.setStoreUsageTime(Input.inputCustomerStoreUsageTime());
                if (choiceMenuNumber == 4) customer.setTotalPaymentAmount(Input.inputCustomerTotalPaymentAmount());
            } catch (InputEndException | InputRangeException | IndexOutOfBoundsException | ArrayEmptyException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private int chooseSetCustomerMenu(){
        while (true) {
            try {
                int inputMenuNumber = Input.chooseMenuNumber(CUSTOMER_SET_MENU_ITEMS);
                if (inputMenuNumber < 1 || inputMenuNumber > CUSTOMER_SET_MENU_ITEMS.length) throw new InputRangeException();
                return inputMenuNumber;
            } catch (InputRangeException | InputFormatException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    private void callSummaryServiceRefresh(){
        SummaryService.getInstance().refreshClassifiedCustomers();
    }
}
