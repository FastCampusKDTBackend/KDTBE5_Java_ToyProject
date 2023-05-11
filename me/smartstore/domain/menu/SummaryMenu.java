package me.smartstore.domain.menu;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.utils.constant.SortType;
import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.Groups;
import me.smartstore.domain.group.Parameter;
import me.smartstore.domain.group.constant.GroupType;
import me.smartstore.utils.exception.InputEndException;
import me.smartstore.utils.exception.InputRangeException;
import me.smartstore.utils.exception.InvalidSortTypeException;

import static me.smartstore.utils.constant.SortType.*;
import static me.smartstore.domain.group.constant.GroupType.*;
import static me.smartstore.utils.constant.Choice.SUMMARY_MENU;
import static me.smartstore.utils.constant.Message.END_MSG;

public class SummaryMenu implements Menu {
    private static final int END_PRESS = 10000;
    private static final int ASCENDING = 1;
    private static final int DESCENDING = -1;
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
            int choice = chooseMenu(SUMMARY_MENU.getChoices());

            if (choice == 1) {
                getSummary(allCustomers.getCustomers());
            } else if (choice == 2) {
                getSortedSummary(CUSTOMER_NAME);
            } else if (choice == 3) {
                getSortedSummary(CUSTOMER_TIME);
            } else if (choice == 4) {
                getSortedSummary(CUSTOMER_PAYMENT);
            } else {
                break;
            }
        }
    }

    private void getSummary(Customer[] customers) {
        GroupType[] types = {NONE, GENERAL, VIP, VVIP};
        for (GroupType type : types) {
            printSummary(customers, type);
        }
    }

    private void getSortedSummary(SortType type) {
        while (true) {
            int classificationVal = getClassificationValue();
            if (classificationVal == END_PRESS) return;
            try {
                getSummary(allCustomers.getSortedCustomers(type.getCustomerComparator(classificationVal)));
            } catch (InvalidSortTypeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    private int getClassificationValue() {
        while (true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String value = nextLine(END_MSG.getMessage());

                if (!value.equals("A") && !value.equals("D")) {
                    throw new InputRangeException();
                }

                if (value.equals("A")) {
                    return ASCENDING;
                }
                return DESCENDING;
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
