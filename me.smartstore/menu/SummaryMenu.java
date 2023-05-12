package menu;

import customer.Customer;
import customer.Customers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SummaryMenu implements Menu {
    private static SummaryMenu summaryMenu;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        Customers allCustomers = Customers.getInstance();

        while (true) {
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            switch (choice) {
                case 1:
                    System.out.println("========== Summary ==========");
                    printCustomers(allCustomers.getList());
                    break;
                case 2:
                    System.out.println("========== Summary (Sorted By Name) ==========");
                    List<Customer> customersSortedByName = allCustomers.getList();
                    Collections.sort(customersSortedByName, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            return o1.getCusName().compareTo(o2.getCusName());
                        }
                    });
                    printCustomers(customersSortedByName);
                    break;
                case 3:
                    System.out.println("========== Summary (Sorted By Time) ==========");
                    List<Customer> customersSortedByTime = allCustomers.getList();
                    Collections.sort(customersSortedByTime, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            return Integer.compare(o2.getCusTotalTime(), o1.getCusTotalTime());
                        }
                    });
                    printCustomers(customersSortedByTime);
                    break;
                case 4:
                    System.out.println("========== Summary (Sorted By Pay) ==========");
                    List<Customer> customersSortedByPay = allCustomers.getList();
                    Collections.sort(customersSortedByPay, new Comparator<Customer>() {
                        @Override
                        public int compare(Customer o1, Customer o2) {
                            return Double.compare(o2.getCusTotalPay(), o1.getCusTotalPay());
                        }
                    });
                    printCustomers(customersSortedByPay);
                    break;
                case 5:
                    return;
            }
        }
    }

    private void printCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
