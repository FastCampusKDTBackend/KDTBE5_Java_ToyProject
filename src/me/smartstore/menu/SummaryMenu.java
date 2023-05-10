package me.smartstore.menu;


import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;

import java.util.Comparator;

import static me.smartstore.util.Message.ERR_MSG_INPUT_END;

public class SummaryMenu implements Menu {


    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    int switcher = 1;

    // singleton
    private static SummaryMenu summaryMenu;

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
        while (true) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});

            if (choice == 1) defaultSummary();
            else if (choice == 2) summaryByName();
            else if (choice == 3) summaryByTime();
            else if (choice == 4) summaryByPay();
            else break; // "Back"
        }
    }

    private void printParameter() {
        System.out.println("==============================");
        System.out.println("Group : NONE ( Time : null, Pay : null )");
        System.out.println("==============================");
    }
    private void printParameter(GroupType groupType) {
        try {
            Group groupPara = allGroups.find(groupType);
            Parameter parameterG = groupPara.getParameter();
            System.out.println("==============================");
            System.out.println("Group : " + groupType + " ( Time : " + parameterG.getMinimumSpentTime()
                    + " pay : " + parameterG.getMinimumTotalPay() + " ) ");
            System.out.println("==============================");
        } catch (NullPointerException e ){
        }
    }
    private void printNone(Comparator<Customer> comparator) {
        int countN = 0;
        printParameter();
            if (comparator == null) {
                Customer[] groupND = allCustomers.toArray();
                for (int i = 0; i < allCustomers.size(); i++) {
                    if (groupND[i].getGroup().getGroupType() == GroupType.NONE) {
                        System.out.println("No.     " + (countN + 1) + " =>" + groupND[i]);
                        countN++;
                    }
                }
            } else if (comparator != null) {
                Customer[] groupN = allCustomers.toArraySort(comparator);
                for (int i = 0; i < allCustomers.size(); i++) {
                    if (groupN[i].getGroup().getGroupType() == GroupType.NONE) {
                        System.out.println("No.     " + (countN + 1) + " =>" + groupN[i]);
                        countN++;
                    }
                }
            }
        if (countN == 0) {
            System.out.println("Null. \n");
        }else {
            System.out.println("==============================\n");
        }
    }

    private void print(GroupType groupType , Comparator<Customer> comparator) {

        int count = 0;
        printParameter(groupType);
        if (comparator == null) {
            Customer[] groupD = allCustomers.toArray();
            for (int i = 0; i < allCustomers.size(); i++) {
                if (groupD[i].getGroup().getGroupType().equals(groupType)) {
                    System.out.println("No.     " + (count + 1) + " =>" + groupD[i]);
                    count++;
                }
            }
        } else if (comparator != null) {
            Customer[] groupS = allCustomers.toArraySort(comparator);
            for (int i = 0; i < allCustomers.size(); i++) {
                if (groupS[i].getGroup().getGroupType().equals(groupType)) {
                    System.out.println("No.     " + (count + 1) + " =>" + groupS[i]);
                    count++;
                }
            }
        }
        if (count == 0) {
            System.out.println("Null. \n");
        } else {
            System.out.println("==============================\n");
        }
    }

    private Comparator<Customer> standardSwitcher(String standard) {

        if (standard == "T") {
            Comparator<Customer> comparator = (c1, c2) -> {
                return switcher * c1.getTotalTime().compareTo(c2.getTotalTime());
            };
            return comparator;
        }
        if (standard == "N" ) {
            Comparator<Customer> comparator = (c1, c2) -> {
                return switcher * c1.getCustomerName().compareTo(c2.getCustomerName());
            };
            return comparator;
        }
        if (standard == "P" ) {
            Comparator<Customer> comparator = (c1, c2) -> {
                return switcher * c1.getTotalPay().compareTo(c2.getTotalPay());
            };
            return comparator;
        }
        if (standard == "D") {
            Comparator<Customer> comparator = null;
        }
        return null;
    }

    private void defaultSummary() {
        printNone(standardSwitcher("D"));
        print(GroupType.GENERAL,standardSwitcher("D"));
        print(GroupType.VIP,standardSwitcher("D"));
        print(GroupType.VVIP,standardSwitcher("D"));
    }

    private void summaryByName() {
        while ( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?\n");
                String str = nextLine("END");
                if (str.equals("A") || str.equals("ASCENDING")) {
                    switcher = 1;
                    printNone(standardSwitcher("N"));
                    print(GroupType.GENERAL,standardSwitcher("N"));
                    print(GroupType.VIP,standardSwitcher("N"));
                    print(GroupType.VVIP,standardSwitcher("N"));
                }
                if (str.equals("D") || str.equals("DESCENDING")) {
                    switcher = -1;
                    printNone(standardSwitcher("N"));
                    print(GroupType.GENERAL,standardSwitcher("N"));
                    print(GroupType.VIP,standardSwitcher("N"));
                    print(GroupType.VVIP,standardSwitcher("N"));
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private void summaryByTime () {
        while ( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?\n");
                String str = nextLine("END");
                if (str.equals("A") || str.equals("ASCENDING")) {
                    switcher = 1;
                    printNone(standardSwitcher("T"));
                    print(GroupType.GENERAL,standardSwitcher("T"));
                    print(GroupType.VIP,standardSwitcher("T"));
                    print(GroupType.VVIP,standardSwitcher("T"));
                }
                if (str.equals("D") || str.equals("DESCENDING")) {
                    switcher = -1;
                    printNone(standardSwitcher("T"));
                    print(GroupType.GENERAL,standardSwitcher("T"));
                    print(GroupType.VIP,standardSwitcher("T"));
                    print(GroupType.VVIP,standardSwitcher("T"));
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }
        }
    }
    private void summaryByPay () {
        while ( true ) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?\n");
                String str = nextLine("END");
                if (str.equals("A") || str.equals("ASCENDING")) {
                    switcher = 1;
                    printNone(standardSwitcher("P"));
                    print(GroupType.GENERAL,standardSwitcher("P"));
                    print(GroupType.VIP,standardSwitcher("P"));
                    print(GroupType.VVIP,standardSwitcher("P"));
                }
                if (str.equals("D") || str.equals("DESCENDING")) {
                    switcher = -1;
                    printNone(standardSwitcher("P"));
                    print(GroupType.GENERAL,standardSwitcher("P"));
                    print(GroupType.VIP,standardSwitcher("P"));
                    print(GroupType.VVIP,standardSwitcher("P"));
                }
            } catch (InputEndException e) {
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }
        }
    }
}
