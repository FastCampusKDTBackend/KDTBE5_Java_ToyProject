package service;

import domain.customer.Customer;
import domain.customer.Customers;
import exception.ArrayEmptyException;
import exception.InputEndException;
import exception.InputRangeException;
import view.Output;
import view.input.CustomerInput;
import view.input.MenuInput;

import java.util.Objects;

public class CustomerService {

    private static CustomerService customerService;
    private static final Customers customers = Customers.getInstance();

    private static final String[] MENU_ITEMS = new String[]{"Customer Name", "Customer ID",
            "Customer Store Usage Time", "Customer Total Payment Amount", "Back"};

    private CustomerService() {

    }

    public static CustomerService getInstance() {
        if (Objects.isNull(customerService)){
            customerService = new CustomerService();
        }
        return customerService;
    }

    private void setCustomer(Customer customer) {
        while (true) {
            try {
                int choiceMenuNumber = chooseSetCustomerMenu();
                if (choiceMenuNumber == 5) break;
                if (choiceMenuNumber == 1) customer.setName(CustomerInput.inputCustomerName());
                if (choiceMenuNumber == 2) customer.setId(CustomerInput.inputCustomerId());
                if (choiceMenuNumber == 3) customer.setStoreUsageTime(CustomerInput.inputCustomerStoreUsageTime());
                if (choiceMenuNumber == 4) customer.setTotalPaymentAmount(CustomerInput.inputCustomerTotalPaymentAmount());
            } catch (InputEndException | InputRangeException | IndexOutOfBoundsException exception) {
                System.out.println(exception.getMessage());
            }
        }
        setCustomerEmptyValueToDefault(customer);
    }

    private void setCustomerEmptyValueToDefault(Customer customer){
        if (Objects.isNull(customer.getId())) customer.setId(String.valueOf(Objects.hash(Math.random())));
        if (Objects.isNull(customer.getName())) customer.setName(String.valueOf(Objects.hash(Math.random())));
        if (Objects.isNull(customer.getStoreUsageTime())) customer.setStoreUsageTime(0);
        if (Objects.isNull(customer.getTotalPaymentAmount())) customer.setTotalPaymentAmount(0);
    }

    private int chooseSetCustomerMenu(){
        while (true) {
            try {
                int inputMenuNumber = MenuInput.chooseMenuNumber(MENU_ITEMS);
                if (inputMenuNumber < 1 || inputMenuNumber > MENU_ITEMS.length) throw new InputRangeException();
                return inputMenuNumber;
            } catch (InputRangeException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    /* ADD CUSTOMER */
    public void addCustomer(int inputCustomerNumber){
        for (int i = 1; i <= inputCustomerNumber; i++) {
            System.out.printf("\n====== Customer %d Info. ======\n", i);
            Customer customer = new Customer();

            setCustomer(customer);

            customers.add(customer);
        }
    }

    /* VIEW CUSTOMER */
    public void viewCustomer() {
        Output.customerList(customers);
    }

    /* UPDATE CUSTOMER */
    public void updateCustomer() {
        if (customers.isEmpty()) throw new ArrayEmptyException();
        viewCustomer();
        setCustomer(customers.get(CustomerInput.inputTargetCustomerNumber()));
    }

    /* DELETE CUSTOMER */
    public void deleteCustomer() {
        if (customers.isEmpty()) throw new ArrayEmptyException();
        viewCustomer();
        customers.pop(CustomerInput.inputTargetCustomerNumber());
    }
}
