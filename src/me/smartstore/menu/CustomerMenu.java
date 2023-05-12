package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Groups;
import me.smartstore.utils.Message;

public class CustomerMenu implements Menu {
    private static CustomerMenu customerMenu;
    private Customers allCustomers;

    public static CustomerMenu getInstance() {
        if(customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    private CustomerMenu() {
        this.allCustomers = Customers.getInstance();
    }


    private final Groups allGroups = Groups.getInstance();

    @Override
    public void manage() {
        while ( true ) {
            int choice = chooseMenu(new String[]{
                    "Add Customer Data",
                    "View Customer Data",
                    "Update Customer Data",
                    "Delete Customer Data",
                    "Back"});

            if (choice == 1) addCustomer();
            else if (choice == 2) viewCustomer();
            else if (choice == 3) updateCustomer();
            else if (choice == 4) deleteCustomer();
            else MainMenu.getInstance().manage();
        }
    }

    private void addCustomer() {
        System.out.println("How many customers to input?");
        int addCustomer = Integer.parseInt(nextLine(Message.END_MSG));
        int cnt = 1;

        while ( cnt <= addCustomer ){

            String cusName = null;
            String cusId = null;
            int cusTotalTime = 0;
            int cusTotalPay = 0;

            Customer customer = new Customer();

            while ( true ) {

                System.out.println("====== Customer " + cnt + " Info. ======");
                int choice = chooseMenu(new String[]{
                        "Customer Name",
                        "Customer ID",
                        "Customer Spent Time",
                        "Customer Total Pay",
                        "Back"});

                if (choice == 1) {
                    cusName = setCustomerName();
                    customer.setCusName(cusName);
                } else if (choice == 2) {
                    cusId = setCustomerID();
                    customer.setCusId(cusId);
                } else if (choice == 3) {
                    cusTotalTime = setCustomerSpentTime();
                    customer.setCusTotalTime(cusTotalTime);
                } else if (choice == 4) {
                    cusTotalPay = setCustomerTotalPay();
                    customer.setCusTotalPay(cusTotalPay);
                } else {
                    allCustomers.add(customer);
                    allCustomers.refresh(allGroups);
                    System.out.println(customer);
                    cnt++;
                    break;
                }
            }
        }
    }

    private String setCustomerName() {
        while ( true ) {
            try {
                System.out.println("Input Customer`s Name: ");
                return nextLine(Message.END_MSG);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private String setCustomerID() {
        while ( true ) {
            try {
                System.out.println("Input Customer`s Id: ");
                return nextLine(Message.END_MSG);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private int setCustomerSpentTime() {
        while ( true ) {
            try {
                System.out.println("Input Customer`s Spent Time: ");
                String minTime = nextLine(Message.END_MSG);
                return Integer.parseInt(minTime);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return 0;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private int setCustomerTotalPay() {
        while ( true ) {
            try {
                System.out.println("Input Customer`s Total Pay: ");
                String minPay = nextLine(Message.END_MSG);
                return Integer.parseInt(minPay);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return 0;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    private void viewCustomer() {
        System.out.println("======= Customer Info. =======");
        allCustomers.viewCustomerData();
    }

    private void updateCustomer() {
        allCustomers.viewCustomerData();
        int customerIdx = nextLine(allCustomers.size());

        int saveIdx = allCustomers.findCustomerDataByCusNo(customerIdx);
        Customer customer = allCustomers.get(saveIdx);

        String cusName = null;
        String cusId = null;
        int cusTotalTime = 0;
        int cusTotalPay = 0;

        while ( true ) {

            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});

            if (choice == 1) {
                cusName = setCustomerName();
                customer.setCusName(cusName);
            } else if (choice == 2) {
                cusId = setCustomerID();
                customer.setCusId(cusId);
            } else if (choice == 3) {
                cusTotalTime = setCustomerSpentTime();
                customer.setCusTotalTime(cusTotalTime);
            } else if (choice == 4) {
                cusTotalPay = setCustomerTotalPay();
                customer.setCusTotalPay(cusTotalPay);
            } else {
                allCustomers.set(saveIdx, customer);
                allCustomers.refresh(allGroups);
                System.out.println(customer);
                manage();
            }
        }
    }

    private void deleteCustomer() {
        allCustomers.viewCustomerData();
        int customerIdx = nextLine(allCustomers.size());
        int saveIdx = allCustomers.findCustomerDataByCusNo(customerIdx);

        allCustomers.pop(saveIdx);

        for (int i = saveIdx; i < allCustomers.size(); i++) {
            Customer customer = allCustomers.get(i);
            System.out.println(customer);
            customer.setCusNo(customer.getCusNo()-1);
            allCustomers.set(i, customer);
        }
    }
}
