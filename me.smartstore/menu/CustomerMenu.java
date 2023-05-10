package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import menu.Menu;
import util.Message;

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

    private CustomerMenu() {}

    @Override
    public void manage() {
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
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
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name;
        try {
            name = nextLine();
        } catch (InputEndException e) {
            return;
        }
        System.out.print("Enter customer Id: ");
        String Id;
        try {
            Id = nextLine();
        } catch (InputEndException e) {
            return;
        }
        System.out.print("Enter cusTotalTime: ");
        int cusTotalTime;
        try {
            cusTotalTime = Integer.parseInt(nextLine());
        } catch (InputEndException e) {
            return;
        }

        System.out.print("Enter cusTotalPay: ");
        int cusTotalPay;
        try {
            cusTotalPay = Integer.parseInt(nextLine());
        } catch (InputEndException e) {
            return;
        }

        // add logic

        System.out.println(name + " has been added.");
    }

    private void viewCustomer() {
        allCustomers.printAll();
    }

    private void updateCustomer() {
        System.out.print("Enter customer name to update: ");
        String name = nextLine(Message.END_MSG);
        if (name == null) return; // input end

        Customer customer = allCustomers.find(name);
        if (customer == null) {
            System.out.println("There is no customer named '" + name + "'");
            return;
        }

        // update logic

        System.out.println(customer.getCusName() + " has been updated.");
    }

    private void deleteCustomer() {
        System.out.print("Enter customer name to delete: ");
        String name = nextLine(Message.END_MSG);
        if (name == null) return; // input end

        Customer customer = allCustomers.find(name);
        if (customer == null) {
            System.out.println("There is no customer named '" + name + "'");
            return;
        }

        allCustomers.remove(customer);
        System.out.println(customer.getCusName() + " has been deleted.");
    }
}
