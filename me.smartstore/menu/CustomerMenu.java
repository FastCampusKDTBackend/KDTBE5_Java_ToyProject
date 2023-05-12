package menu;

import customer.Customer;
import customer.Customers;
import exception.InputEndException;
import util.Message;

public class CustomerMenu implements Menu {
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
        while (true) {
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
                break;
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


        System.out.println(name + " has been added.");
    }

    private void viewCustomer() {
        if (allCustomers.isEmpty()) {
            System.out.println("There are no customers.");
        } else {
            System.out.println("Customers:");
            for (Customer c : allCustomers.getList()) {
                System.out.println("- " + c.getCusName() + " (ID: " + c.getCusId() + ")"+ " (TotalPay: " + c.getCusTotalPay() + ")"+ " (TotalTime: " + c.getCusTotalTime() + ")");
            }
        }
    }


    private void updateCustomer() {
        System.out.print("Enter customer name to update: ");
        String name = nextLine(Message.END_MSG);
        if (name == null) return; // input end

        Customer customer = null;
        for (Customer c : allCustomers.getList()) {
            if (c.getCusName().equalsIgnoreCase(name)) {
                customer = c;
                break;
            }
        }
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

        Customer customer = allCustomers.find(name.toLowerCase()); // 소문자로 변환하여 검색
        if (customer == null) {
            System.out.println("There is no customer named '" + name + "'");
            return;
        }

        if (customer != null) {
            allCustomers.remove(customer);
            System.out.println(customer.getCusName() + " has been deleted.");
        }
    }
}
