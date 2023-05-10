package domain.menu;

import Service.SummaryService;
import domain.customer.Customers;
import domain.group.Groups;

public class SummaryMenu implements Menu {

    private final Customers customers = Customers.getInstance();
    private final Groups groups = Groups.getInstance();

    // singleton
    private final SummaryService summaryService = SummaryService.getInstance();
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
        while (true) {
            int choice = summaryMenu.selectMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });

            if (choice == 1) summaryService.showDefault(customers, groups);
            if (choice == 2) summaryService.sortByName(customers, summaryMenu, groups);
            if (choice == 3) summaryService.sortByTime(customers, summaryMenu, groups);
            if (choice == 4) summaryService.sortByPayment(customers, summaryMenu, groups);
            if (choice == 5) break;
        }
    }
}

