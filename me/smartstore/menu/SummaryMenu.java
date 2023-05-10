package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.EmptyArrayException;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Comparator;


public class SummaryMenu implements Menu {
    private static SummaryMenu summaryMenu;
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if (choice == 1) summary();
            else if (choice == 2) sortedByName();
            else if (choice == 3) sortedByTime();
            else if (choice == 4) sortedByPay();
            else break;
        }
    }

    private void summary() {
        try {
            for (int i = 0; i < allCustomers.size(); i++) {
                System.out.println(allCustomers.get(i).toString());
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }

    public void sortedByName() {
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers);

            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
        }
    }

    public void sortedByTime() {
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers, Comparator.comparing(Customer::getCusTotalTime));

            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }

    public void sortedByPay() {
        try {
            Customer[] customers = new Customer[allCustomers.size()];
            for (int i = 0; i < customers.length; i++) {
                customers[i] = allCustomers.get(i);
            }
            Arrays.sort(customers, Comparator.comparing(Customer::getCusTotalPay));

            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (EmptyArrayException e) {
            System.out.println(Message.ERR_MSG_NULL_ARR_ELEMENT);
        }
    }
}


