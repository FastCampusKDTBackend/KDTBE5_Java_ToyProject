package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.constant.GroupType;
import me.smartstore.domain.group.Groups;
import me.smartstore.domain.group.Parameter;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import java.util.Comparator;

public class SummaryMenu implements Menu {
    private static final int END_PRESS = 10000;
    private static SummaryMenu summaryMenu;
    private final Customers allCustomers;
    private final Groups allGroups;

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {
        this.allCustomers = Customers.getInstance();
        this.allGroups = Groups.getInstance();
    }

    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });

            if (choice == 1) {
                getSummary(allCustomers.getCustomers());
            } else if (choice == 2) {
                getSummarySortedByName();
            } else if (choice == 3) {
                getSummarySortedByTime();
            } else if (choice == 4) {
                getSummarySortedByTotalPayment();
            } else {
                break;
            }
        }
    }

    private void getSummary(Customer[] customers) {
        printSummary(customers, GroupType.NONE);
        printSummary(customers, GroupType.GENERAL);
        printSummary(customers, GroupType.VIP);
        printSummary(customers, GroupType.VVIP);
    }

    private void getSummarySortedByName() {
        while (true) {
            int classificationVal = getClassificationValue();
            if (classificationVal == END_PRESS) return;
            Comparator<Customer> comparator = (customer1, customer2) -> {
                return (classificationVal) * customer1.getCustomerName().compareTo(customer2.getCustomerName());
            };
            getSummary(allCustomers.getSortedCustomers(comparator));
        }
    }

    private void getSummarySortedByTime() {
        while (true) {
            int classificationVal = getClassificationValue();
            if (classificationVal == END_PRESS) return;
            Comparator<Customer> comparator = (customer1, customer2) -> {
                return (classificationVal) * customer1.getSpentTime().compareTo(customer2.getSpentTime());
            };
            getSummary(allCustomers.getSortedCustomers(comparator));
        }
    }

    private void getSummarySortedByTotalPayment() {
        while (true) {
            int classificationVal = getClassificationValue();
            if (classificationVal == END_PRESS) return;
            Comparator<Customer> comparator = (customer1, customer2) -> {
                return (classificationVal) * customer1.getTotalPay().compareTo(customer2.getTotalPay());
            };
            getSummary(allCustomers.getSortedCustomers(comparator));
        }
    }

    private int getClassificationValue() {
        while (true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String value = nextLine("END");

                if (!value.equals("A") && !value.equals("D")) {
                    throw new InputRangeException();
                }

                if (value.equals("A")) {
                    return 1; // 오름차순
                }
                return -1; // 내림차순
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
        return END_PRESS;
    }

    private void printSummary(Customer[] customers, GroupType type) {
        StringBuilder builder = new StringBuilder();
        Parameter parameter = null;
        Group group = null;
        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getGroupType() == type) {
                group = allGroups.get(i);
                parameter = group.getParameter();
            }
        }
        int number = 0;
        builder.append("==============================").append("\n");
        builder.append("Group : ").append(type.name());
        builder.append(String.format(" ( Time : %d, Pay : %d )",
                parameter != null ? parameter.getMinUsageTime() : null,
                parameter != null ? parameter.getMinPurchaseAmount() : null)).append("\n");
        builder.append("==============================").append("\n");


        if (group != null) {
            for (int i = 0; i < customers.length; i++) {
                Customer customer = customers[i];
                if (customer.getGroup().equals(group)) {
                    builder.append("No. ").append(++number).append(" => ").append(customer).append("\n");
                }
            }
        }

        if (group == null || number == 0) {
            builder.append("Null.").append("\n");
        }

        builder.append("==============================").append("\n");
        System.out.println(builder);
    }
}
