package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.InvalidSortTypeException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.util.Message;
import me.smartstore.util.SortType;

import static me.smartstore.util.SortType.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SummaryMenu implements Menu {

    private static SummaryMenu summaryMenu;
    private final Groups allGroups;
    private final Customers allCustomers;

    private static final int ASCENDING = 1;
    private static final int DESCENDING = -1;
    private static final int END = 100;

    private SummaryMenu() {
        this.allGroups = Groups.getInstance();
        this.allCustomers = Customers.getInstance();
    }

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    /**
     * 요약 사항 관리 메서드
     */
    @Override
    public void manage() {
        while (true) {
            int choice = chooseMenu(new String[] {
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if (choice == 1) getSummary(allCustomers.getAllCustomers());
            else if (choice == 2) getSortedSummary(CUSTOMER_NAME);
            else if (choice == 3) getSortedSummary(CUSTOMER_TIME);
            else if (choice == 4) getSortedSummary(CUSTOMER_PAYMENT);
            else break;
        }
    }

    private void getSortedSummary(SortType type) {
        while (true) {
            int classificationValue = getClassificationValue();
            try {
                getSummary(allCustomers.getSortedCustomers(type.getCustomerComparator(classificationValue)));
            } catch (InvalidSortTypeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);
                return;
            }
        }
    }

    private int getClassificationValue() {
        while (true) {
            try {
                System.out.println("Which order ASCENDING(A) or DESCENDING(D)?");
                String str = nextLine(Message.END_MSG);

                if (!str.equals("A") && !str.equals("D")) throw new InputRangeException();

                if (str.equals("A")) return ASCENDING;
                else return DESCENDING;
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
        return END;
    }

    private void getSummary(Customer[] customers) {
        GroupType[] types = GroupType.getAllGroupTypes();
        for (GroupType type : types) {
            printSummary(customers, type);
        }
    }

    private void printSummary(Customer[] customers, GroupType groupType) {
//        System.out.println("==============================");
//        System.out.printf("Group : %s ( Time : %s, Pay : %s )\n", group.getGroupType(), group.getParameter().getMinTime(), group.getParameter().getMinPay());
//        System.out.println("==============================");

        Group group = null;
        Parameter parameter = null;

        for (int i=0; i< allGroups.size(); i++) {
            if (allGroups.get(i).getGroupType() == groupType) {
                group = allGroups.get(i);
                parameter = group.getParameter();
            }
//            System.out.println("==============================");
//            System.out.printf("Group : %s ( Time : %s, Pay : %s )\n", groupType.name(), parameter.getMinTime(), parameter.getMinPay());
        }

        System.out.println("==============================");
        System.out.printf("Group : %s ( Time : %s, Pay : %s )\n", groupType.name(), parameter.getMinTime(), parameter.getMinPay());

        if (group != null) {
            for (int i=0; i<customers.length; i++) {
                Customer customer = customers[i];
                if (customer.getGroup().equals(group)) {
                    System.out.println(String.format("No. %d =>", (i + 1)) + allCustomers.get(i));
                }
            }
        }
        else if (group == null) {
            System.out.println("Null");
        }
    }
}
