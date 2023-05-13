package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.EmptyArrayException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;


public class CustomerMenu implements Menu {

    private static CustomerMenu customerMenu;
    private final Customers allCustomers;

    private CustomerMenu() {
        this.allCustomers = Customers.getInstance();
    }

    public static CustomerMenu getInstance() {
        if (customerMenu == null) {
            customerMenu = new CustomerMenu();
        }
        return customerMenu;
    }

    /**
     * 고객 관리 담당 메서드
     *
     */
    @Override
    public void manage() {
        while (true) {
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
            else break;
        }
    }

    private void addCustomer() {
        while (true) {
            System.out.println("How many customers to input?");
            try {
                Integer addCustomerNum = Integer.parseInt(nextLine(Message.END_MSG));
                if (addCustomerNum <= 0) {
                    throw new InputRangeException();
                }
                for (int i=0; i<addCustomerNum; i++) {
                    System.out.println(String.format("====== Customer %d Info. ======", i+1));
                    Customer customer = new Customer();
                    makeCustomerInfo(customer);
                    allCustomers.add(customer);
                }
                allCustomers.refresh();
                return;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private void makeCustomerInfo(Customer customer) {
        boolean isCompleted = false;
        while (! isCompleted) {
            int choice = chooseMenu(new String[] {
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});
            try {
                if (choice == 1) {
                    System.out.println("\nInput Customer's Name : ");
                    customer.setCustomerName(nextLine(Message.END_MSG));
                }
                else if (choice == 2) {
                    System.out.println("\nInput Customer's ID : ");
//                    customer.setCustomerId(nextLine(Message.END_MSG));
                    Integer id = Integer.parseInt(nextLine(Message.END_MSG));
                    if (id < 0) throw new InputRangeException(Message.ERR_MSG_INVALID_INPUT_RANGE);

                }
                else if (choice == 3) {
                    System.out.println("\nInput Customer's Spent Time : ");
                    Integer spentTime = Integer.parseInt(nextLine(Message.END_MSG));
                    if (spentTime < 0) {
                        throw new InputRangeException();
                    }
                    customer.setCustomerTotalTime(spentTime);
                }
                else if (choice == 4) {
                    System.out.println("\nInput Customer's Spent Payment : ");
                    Integer spentMoney = Integer.parseInt(nextLine(Message.END_MSG));
                    if (spentMoney < 0) {
                        throw new InputRangeException();
                    }
                    customer.setCustomerTotalPay(spentMoney);
                }
                else {
                    isCompleted = true;
                }
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    /**
     *
     */
    private void viewCustomer() {
        try {
            if (allCustomers.size() == 0) {
                throw new EmptyArrayException();
            }
            System.out.println("\n======= Customer Info. =======");
            for (int i = 0; i < allCustomers.size(); i++) {
                //System.out.println("No. %d => " + allCustomers.get(i), (i+1));
                System.out.println(String.format("No. %d =>", (i + 1)) + allCustomers.get(i));
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_ARR_EMPTY);
        }
    }

    private void updateCustomer() {
        while (true) {
            viewCustomer();
            try {
                System.out.printf(String.format("\nWhich customer do you want to update? (%d ~ %d)", 1, allCustomers.size()));
                Integer choice = Integer.parseInt(nextLine(Message.END_MSG));
                if (choice < 1 || choice > allCustomers.size()) throw new InputRangeException();
                Customer customer = allCustomers.get(choice-1);
                makeCustomerInfo(customer);
                allCustomers.refresh();
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

//    private void deleteCustomer() {
//        while (true) {
//            try {
//                if (allCustomers.size() == 0) {
//                    throw new EmptyArrayException();
//                }
//                for (int i = 0; i < allCustomers.size(); i++) {
//                    //System.out.println("No. %d => " + allCustomers.get(i), (i+1));
//                    System.out.println(String.format("No. %d =>", (i + 1)) + allCustomers.get(i));
//                }
//                //System.out.println("\nWhich customer (%d ~ %d)", 1, allCustomers.size()+1);
//                System.out.println(String.format("\nWhich customer (%d ~ %d)", 1, allCustomers.size()));
//                Integer choice = Integer.parseInt(nextLine());
//            } catch (EmptyArrayException e) {
//                System.out.println(Message.ERR_MSG_ARR_EMPTY);
//            }
//        }
//    }

    private void deleteCustomer() {
        while (true) {
            try {
                viewCustomer();
                System.out.println(String.format("\nWhich customer do you want to delete? (%d ~ %d)", 1, allCustomers.size()));
                Integer choice = Integer.parseInt(nextLine(Message.END_MSG));
                System.out.println(allCustomers.pop(choice-1));
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                break;
            }
        }
    }

//    private void printCustomerList() {
//        try {
//            if (allCustomers.size() == 0) {
//                throw new EmptyArrayException();
//            }
//            System.out.println("\n======= Customer Info. =======");
//            for (int i = 0; i < allCustomers.size(); i++) {
//                //System.out.println("No. %d => " + allCustomers.get(i), (i+1));
//                System.out.println(String.format("No. %d =>", (i + 1)) + allCustomers.get(i));
//            }
//        } catch (Exception e) {
//
//        }
//    }
}
