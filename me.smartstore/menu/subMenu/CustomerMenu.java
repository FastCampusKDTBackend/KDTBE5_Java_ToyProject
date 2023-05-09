package menu.subMenu;

import domain.customer.Customer;
import exception.*;
import menu.MenuManager;
import service.CustomerService;
import service.SummaryService;
import util.view.Input;
import util.view.Output;

public class CustomerMenu implements SubMenu {

    private static final String[] MENU_ITEMS;
    private static final String[] SUB_MENU_ITEMS;

    static {
        MENU_ITEMS = new String[]{"Add Customer Data", "View Customer Data", "Update Customer Data", "Delete Customer Data", "Back"};
        SUB_MENU_ITEMS = new String[]{"Customer Name", "Customer ID", "Customer Store Usage Time", "Customer Total Payment Amount", "Back"};
    }

    private final CustomerService customerService;
    private final SummaryService summaryService;

    public CustomerMenu(CustomerService customerService, SummaryService summaryService) {
        this.customerService = customerService;
        this.summaryService = summaryService;
    }

    @Override
    public void service() {
        while (true) {
            try {
                int choiceMenuNum = MenuManager.chooseMenu(MENU_ITEMS);
                if (choiceMenuNum == 1) addCustomer();
                if (choiceMenuNum == 2) viewCustomer();
                if (choiceMenuNum == 3) updateCustomer();
                if (choiceMenuNum == 4) deleteCustomer();
            } catch (InputEndException | ArrayEmptyException exception) {
                Output.printErrorMessage(exception.getMessage());
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
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
                int choiceMenuNumber = MenuManager.chooseMenu(SUB_MENU_ITEMS);
                if (choiceMenuNumber == 1) customer.setName(Input.inputCustomerName());
                if (choiceMenuNumber == 2) customer.setId(Input.inputCustomerId());
                if (choiceMenuNumber == 3) customer.setStoreUsageTime(Input.inputCustomerStoreUsageTime());
                if (choiceMenuNumber == 4) customer.setTotalPaymentAmount(Input.inputCustomerTotalPaymentAmount());
            } catch (InputEndException | InputRangeException | IndexOutOfBoundsException | ArrayEmptyException exception) {
                System.out.println(exception.getMessage());
            } catch (InputNumOfEndMenuException exception) {
                break;
            }
        }
    }

    private void callSummaryServiceRefresh(){
        summaryService.refreshClassifiedCustomers();
    }
}
