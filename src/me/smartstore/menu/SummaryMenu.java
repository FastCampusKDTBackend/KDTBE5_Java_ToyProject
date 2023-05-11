package me.smartstore.menu;


import me.smartstore.customers.Customer;
import me.smartstore.customers.Customers;
import me.smartstore.groups.Group;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Groups;
import me.smartstore.util.Board;
import me.smartstore.util.Message;
import me.smartstore.util.exception.InputEndException;
import me.smartstore.util.exception.InputFormatException;

import java.util.Comparator;

import static me.smartstore.groups.GroupType.*;

public class SummaryMenu implements Menu {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    // singleton

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
        while ( true ) {
            int choice = chooseMenu(Board.SUMMARY_MENU.getBoard());

            if (choice == 1) getSummary(allCustomers.initCustomers());
            else if (choice == 2) getSortSummary(SortType.BYNAME);
            else if (choice == 3) getSortSummary(SortType.BYTIME);
            else if (choice == 4) getSortSummary(SortType.BYPAY);
            else break;
        }
    }
    private void getSummary(Customer[] customers) {
        allCustomers.refresh();
        GroupType[] groupType = {NONE, GENERAL, VIP, VVIP};

        for (GroupType type : groupType) {
            displaySummary(customers, type);
        }
    }
    private void displaySummary(Customer[] customers, GroupType type) {
        Group group = allGroups.find(type);
        Integer time = null;
        Integer pay = null;

        int count = 1;
        if (group != null) {
            time = group.getParmeter().getMinTime();
            pay = group.getParmeter().getMinPayment();

            System.out.println("==============================");
            System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", type, time, pay);
            System.out.println("==============================");

            for (int i = 0; i < allCustomers.size(); i++) {
                if (customers[i].getGroup().getGroupType().equals(type)) {
                    System.out.printf("No. %d => %s\n", count++, customers[i]);
                }
            }
        }
        else if (group == null) {
            System.out.println("==============================");
            System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", type, time, pay);
            System.out.println("==============================");
            System.out.println(group);
        }
        System.out.println("==============================\n");
    }

    /*
    * Comparator<Customer> comparator
    * 따로 구현 enum으로 구현
    * @TODO Refactoring
    * */
    private void getSortSummary(SortType sortType) {
        while ( true ) {
            try {
                System.out.println("\nWhich order (ASCENDING (A), DESCENDING (D))?");
                String choice = nextLine(Message.END_MSG);
                int positiveNum = 0;

                if (choice.equals("A") || choice.equals("ASCENDING")) {
                    positiveNum = 1;
                } else if (choice.equals("D") || choice.equals("DESCENDING")) {
                    positiveNum = -1;
                }

                if (sortType == SortType.BYNAME) {
                    getSummary(allCustomers.sortSummary(allCustomers.sortName(positiveNum)));
                }
                else if (sortType == SortType.BYTIME) {
                    getSummary(allCustomers.sortSummary(allCustomers.sortTime(positiveNum)));
                }
                else if (sortType == SortType.BYPAY) {
                    getSummary(allCustomers.sortSummary(allCustomers.sortPay(positiveNum)));
                }

            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                break;

            } catch (InputFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE);

            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }

        }

    }

}
