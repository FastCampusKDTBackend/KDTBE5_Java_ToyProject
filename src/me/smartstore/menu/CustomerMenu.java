package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.util.Message;

import static me.smartstore.util.Message.*;

public class CustomerMenu implements Menu {

    private final Customers allCustomers = Customers.getInstance();
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
            if (choice == 1) addCustomer();
            else if (choice == 2) viewCustomer();
            else if (choice == 3) updateCustomer();
            else if (choice == 4) deleteCustomer();
            else break; // "Back"
        }
    }

    private void setCustomerManage(Customer customer) {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer Id",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});
            if (choice == 1) {
                System.out.println("\nInput Customer's Name: ");
                String input = nextLine(END_MSG);
                customer.setCustomerName(input);
            }
            else if (choice == 2) {
                System.out.println("\nInput Customer's ID: ");
                String input = nextLine(END_MSG);
                customer.setCustomerId(input);
            }
            else if (choice == 3) {
                System.out.println("\nInput Customer's Spent Time: ");
                int input = Integer.parseInt(nextLine(END_MSG));
                customer.setTotalTime(input);
            }
            else if (choice == 4) {
                System.out.println("\nInput Customer's Total Payment: ");
                int input = Integer.parseInt(nextLine(END_MSG));
                customer.setTotalPay(input);
            }
            else {break;}
        }
    }
    private void addCustomer() {
        while (true) {
            try {
                System.out.println("How many customers to input?");
                int input = Integer.parseInt(nextLine(END_MSG));
                for (int i = 1; i <= input; i++) {
                    System.out.println("====== Customer " + i + " Info. ======");
                    Customer customer = new Customer();
                    setCustomerManage(customer);
                    allCustomers.add(customer);

                    allCustomers.refresh();
                }
                break;
            } catch (NullPointerException e ) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputEndException e ) {
                break;
            }
        }
    }

    private void viewCustomer() {
        try {
            System.out.println("\n ======= Customer Info. ======= ");
            if (allCustomers.size() == 0) System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            for (int i = 0; i < allCustomers.size(); i++) {
                    System.out.println("No. " + (i + 1) + "  => " + allCustomers.get(i));
            }

        } catch (NullPointerException e ) {

        }catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
        } catch (InputEmptyException e) {
            System.out.println(ERR_MSG_INVALID_INPUT_NULL);
        }
    }

    private void updateCustomer() {
        try {
            if (allCustomers.size() == 0  ) System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            else if (!(allCustomers.size() == 0)) {
                viewCustomer();
                System.out.printf("Which customer (1 ~ %d)? \n", allCustomers.size());
                int input = Integer.parseInt(nextLine(END_MSG));
                Customer customer = allCustomers.get(input - 1);
                setCustomerManage(customer);
                allCustomers.refresh();
            }
        } catch (NullPointerException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
        }  catch (InputEndException e) {
            System.out.println(ERR_MSG_INVALID_INPUT_EMPTY);
        }
    }

    private void deleteCustomer() {
        try {
            if (allCustomers.size() == 0  ) System.out.println(ERR_MSG_INVALID_ARR_EMPTY);
            else if (!(allCustomers.size() == 0)) {
                viewCustomer();
                System.out.printf("Which customer (1 ~ %d)? \n", allCustomers.size());
                int input = Integer.parseInt(nextLine(END_MSG));
                allCustomers.pop(input - 1);
                allCustomers.refresh();
            }
        } catch (InputEndException e) {
            System.out.println(ERR_MSG_INPUT_END);
        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
        }
    }
}
