package me.smartstore.menu;

import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;

public class SummaryMenu implements Menu {

    private static SummaryMenu summaryMenu;

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance(){
        if(summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});

            if (choice == 1) showSummary();
            else if (choice == 2) showSummarySortedByName();
            else if (choice == 3) showSummarySortedBySpentTime();
            else if (choice == 4) showSummarySortedByTotalPayment();
            else MainMenu.getInstance().manage();
        }
        
        
    }

    private void showSummary() {
        //출력문 통합?

        System.out.println("==============================");
        System.out.println("Group : " + GroupType.GENERAL +
                " ( Time : " + allGroups.find(GroupType.GENERAL).getParameter().getMinTime() + ", " +
                "Pay : " + allGroups.find(GroupType.GENERAL).getParameter().getMinPay() + " )"
        );
        System.out.println("==============================");
        allCustomers.viewCustomerData(GroupType.GENERAL);

//        System.out.println("Group : " + GroupType.NONE + "( Time" + allGroups.get(GroupType.NONE) + "");
    }

    private void showSummarySortedByName() {
        System.out.println("showSummarySortedByName 구현");
    }

    private void showSummarySortedBySpentTime() {
        System.out.println("showSummarySortedBySpentTime 구현");
    }

    private void showSummarySortedByTotalPayment() {
        System.out.println("showSummarySortedByTotalPayment 구현");
    }
}

