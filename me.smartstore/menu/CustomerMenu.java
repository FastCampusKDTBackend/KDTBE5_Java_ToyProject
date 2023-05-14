package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import exception.InputRangeException;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import util.Message;

import java.util.InputMismatchException;


public class CustomerMenu implements Menu {

    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

    private static CustomerMenu customerMenu;

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
        System.out.println();
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            try {
                int choice = chooseMenu(new String[]{
                        "Add Customer",
                        "View Customer",
                        "Update Customer",
                        "Delete Customer",
                        "Back"});
                if (choice == 1) {
                    addCustomer();
                } else if (choice == 2) {
                    viewCustomer();
                } else if (choice == 3) {
                    updateCustomer();
                } else if (choice == 4) {
                    deleteCustomer();
                } else {
                    break; // choice == 5
                }
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
            }
        }
    }

    public void addCustomer() {
        System.out.println("How many customers to input?");
        String numStr = nextLine(Message.END_MSG);
        try {
            Integer num = Integer.parseInt(numStr);
            for (int i = 1; i <= num; i++) {
                System.out.println("\n" + "====== Customer " + i + " Info. ======" + "\n");
                addDetailCustomer();

            }
        } catch (NumberFormatException e) {
            System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
        }
    }

    public void addDetailCustomer() {
        Customer customer = new Customer();
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});

            if (choice == 1) {
                System.out.println("\n" + "Input Customer's Name:");
                String name = nextLine(Message.END_MSG);
                customer.setCustomerName(name);

            } else if (choice == 2) {
                System.out.println("\n" + "Input Customer's ID:");
                String id = nextLine(Message.END_MSG);
                customer.setCustomerID(id);

            } else if (choice == 3) {
                System.out.println("\n" + "Input Customer's Spent Time:");
                Integer time = nextInt(Message.END_MSG);
                customer.setTotalTime(time);

            } else if (choice == 4) {
                System.out.println("\n" + "Input Customer's Total Pay:");
                Integer pay = nextInt(Message.END_MSG);
                customer.setTotalPay(pay);
            } else {
                setCustomerGroup(customer);
                allCustomers.add(customer);
                break; // choice == 5
            }
        }

    }

    private void setCustomerGroup(Customer customer) {
        Group highestGroup = null;
        for (int j = 0; j < allGroups.size(); j++) {
            Group group = allGroups.get(j);
            Parameter parameter = group.getParameter();
            Integer minTime = parameter.getMinTime();
            Integer minPay = parameter.getMinPay();

            Integer optionalCustomerTime = customer.getTotalTime() == null ? 0 : customer.getTotalTime();
            Integer optionalCustomerPay = customer.getTotalPay() == null ? 0 : customer.getTotalPay();

            if (minTime <= optionalCustomerTime && minPay <= optionalCustomerPay) {
                if (highestGroup == null || (parameter.getMinPay() > highestGroup.getParameter().getMinPay() && parameter.getMinTime() > highestGroup.getParameter().getMinTime())) {
                    highestGroup = group;
                }
            }
        }
        if (highestGroup != null) {
            customer.setGroup(highestGroup);
        }
    }

    public void viewCustomer() {
        System.out.println("\n" + "======= Customer Info. =======");
        int num = allCustomers.size();
        for(int i=1; i<=num; i++){
            System.out.print("No. " + i + " => ");
            System.out.println(allCustomers.get(i-1));
        }
    }

    public void updateCustomer() {
        viewCustomer();
        System.out.println("\n" + "Which customer ( 1 ~ " + allCustomers.size() + " )?");

        Integer customerNO = chooseCustomer();
        Customer customer = allCustomers.get(customerNO-1);
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Customer Name",
                    "Customer ID",
                    "Customer Spent Time",
                    "Customer Total Pay",
                    "Back"});

            if (choice == 1) {
                System.out.println("\n" + "Input Customer's Name:");
                String name = nextLine(Message.END_MSG);
                customer.setCustomerName(name);

            } else if (choice == 2) {
                System.out.println("\n" + "Input Customer's ID:");
                String id = nextLine(Message.END_MSG);
                customer.setCustomerID(id);

            } else if (choice == 3) {
                System.out.println("\n" + "Input Customer's Spent Time:");
                Integer time = nextInt(Message.END_MSG);
                customer.setTotalTime(time);

            } else if (choice == 4) {
                System.out.println("\n" + "Input Customer's Total Pay:");
                Integer pay = nextInt(Message.END_MSG);
                customer.setTotalPay(pay);
            } else {
                customer.setGroup(null);
                setCustomerGroup(customer);
                break; // choice == 5
            }
        }
    }


    public int chooseCustomer() {
        while ( true ) {
            try {
                Integer customerNum = 0;
                try {
                    customerNum = nextInt();
                } catch (NumberFormatException e) {
                    throw new InputMismatchException();
                }
                if (customerNum >= 1 && customerNum <= allCustomers.size())
                    return customerNum;
                throw new InputRangeException(); // choice 가 범위에 벗어
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    public void deleteCustomer() {
        viewCustomer();
        System.out.println("\n" + "Which customer ( 1 ~ " + allCustomers.size() + " )?");
        Integer customerNO = chooseCustomer();
        allCustomers.pop(customerNO-1);
        viewCustomer();
    }
}
