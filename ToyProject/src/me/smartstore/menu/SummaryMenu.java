package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;

import java.util.Map;

public class SummaryMenu implements Menu {

    private static SummaryMenu summaryMenu;
    private final Groups allGroups;
    private final Customers allCustomers;

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
            if (choice == 1) printSummary(allGroups, allCustomers);
            else if (choice == 2) summarySortedByName();
            else if (choice == 3) summarySortedByTime();
            else if (choice == 4) summarySortedByPay();
            else break;
        }
    }

    private void printSummary(Groups groups, Customers customers) {
        try {
            System.out.println("[test] groups size : " + groups.size());
            for (int i=0; i<groups.size(); i++) {
                Group group = groups.get(i);
                System.out.println("==============================");
                System.out.printf("Group : %s ( Time : %s, Pay : %s )\n", group.getGroupType(), group.getParameter().getMinTime(), group.getParameter().getMinPay());
                System.out.println("==============================");

                int customerIndex = 1;
                boolean foundCustomer = false;

                for (int j=0; j<customers.size(); j++) {
                    Customer customer = customers.get(j);
                    if (customer.getGroup() != null && customer.getGroup().getGroupType() == group.getGroupType()) {
                        foundCustomer = true;
                        System.out.printf("No. %4d => %s\n", customerIndex, allCustomers.get(j));
                        customerIndex++;
                    }
                }

                if (! foundCustomer) {
                    System.out.println("Null");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    private void printSummary(Groups groups, Customers customers) {
//            try {
//                Map<GroupType, >
//                Groups groups = allGroups.
//                Customers customers = Customers.getAllCustomers();
//                System.out.println("test : " + customers);
//                for (GroupType type : GroupType.values()) {
//
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//    }

    private void summarySortedByName() {

    }

    private void summarySortedByTime() {

    }

    private void summarySortedByPay() {

    }
}
