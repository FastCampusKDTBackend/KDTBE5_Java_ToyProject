package me.smartstore.menu;

import me.smartstore.customers.Customer;
import me.smartstore.customers.Customers;
import me.smartstore.util.Board;
import me.smartstore.util.Message;
import me.smartstore.util.exception.*;

public class CustomerMenu implements Menu {

    // singleton
    private static CustomerMenu customerMenu;
    private final Customers allCustomers = Customers.getInstance();

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(Board.CUSTOMER_MENU.getBoard());

            if (choice == 1) addCustomer();
            else if (choice == 2) viewCustomer();
            else if (choice == 3) updateCustomer();
            else if (choice == 4) deleteCustomer();
            else break;

        }
    }

    private void addCustomer() {
        while (true) {
            try {
                System.out.println("How many customers to input?");
                String choice = nextLine(Message.END_MSG);
                Integer addNum = Integer.parseInt(choice);

                for (int i = 1; i < addNum + 1; i++) {
                    System.out.printf("====== Customer %d Info. ======\n", i);
                    Customer customer = new Customer();
                    customerInfo(customer);
                    allCustomers.add(customer);
                }
                allCustomers.refresh();
                return;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;

            } catch (IllegalArgumentException | InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);

            } catch (NullArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_NULL);
            }

        }
    }

    private void customerInfo(Customer customer) {
        while (true) {
            int choice = chooseMenu(Board.CUSTOMER_ADD_MENU.getBoard());

            if (choice == 1) setCustomerName(customer);
            else if (choice == 2) setCustomerId(customer);
            else if (choice == 3) setCustomerSpentTime(customer);
            else if (choice == 4) setCustomerTotalPay(customer);
            else break;
        }
    }

    private void setCustomerName(Customer customer) {
        try {
            System.out.println("Input Customer's Name: ");
            customer.setcName(nextLine(Message.END_MSG));

        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END);
        }
    }

    private void setCustomerId(Customer customer) {
        try {
            System.out.println("Input Customer's ID: ");
            customer.setcId(nextLine(Message.END_MSG));

        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END);

        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
        }
    }

    private void setCustomerSpentTime(Customer customer) {
        try {
            System.out.println("Input Customer's Spent Time: ");
            Integer time = Integer.parseInt(nextLine(Message.END_MSG));
            if (time < 0) throw new InputRangeException();
            customer.setTotalTime(time);

        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END);

        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);

        } catch (InputRangeException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    private void setCustomerTotalPay(Customer customer) {
        try {
            System.out.println("Input Customer's Total Pay: ");
            Integer pay = Integer.parseInt(nextLine(Message.END_MSG));

            if (pay < 0) throw new InputRangeException();
            customer.setTotalPay(pay);

        } catch (InputEndException e) {
            System.out.println(Message.ERR_MSG_INPUT_END);

        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);

        } catch (InputRangeException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
        }
    }

    private void viewCustomer() {
        try {
            if (allCustomers.size() == 0) {
                throw new EmptyArrayException();
            } else {
                allCustomers.refresh();
                System.out.println("======= Customer Info. =======\n");
                for (int i = 0; i < allCustomers.size(); i++) {
                    System.out.printf("No. %d => %s\n", i + 1, allCustomers.get(i));
                }
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
        }

    }

    private void updateCustomer() {
        viewCustomer();
        while ( true ) {
            System.out.printf("\nWhich customer ( 1 ~ %d )?", allCustomers.size());

            try {
                Integer customerNumber = Integer.parseInt(nextLine(Message.END_MSG));
                if (customerNumber < 0 || customerNumber > allCustomers.size()) {
                    throw new InputRangeException();
                }

                Customer customer = allCustomers.get(customerNumber - 1);
                customerInfo(customer);
                allCustomers.refresh();
                return;

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);

            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private void deleteCustomer() {
        viewCustomer();
        while ( true ) {
            System.out.printf("\nWhich customer ( 1 ~ %d )?", allCustomers.size());

            try {
                Integer customerNumber = Integer.parseInt(nextLine(Message.END_MSG));
                if (customerNumber < 0 || customerNumber > allCustomers.size()) {
                    throw new InputRangeException();
                }

                System.out.println(allCustomers.pop(customerNumber - 1));
                viewCustomer();
                return;

            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);

            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
}
