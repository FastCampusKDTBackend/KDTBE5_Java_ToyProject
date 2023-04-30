package me.smartstore.domain.menu;

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
        while (true) {
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Spent Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });

            if (choice == 1) {
                getSummary();
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

    private void getSummary() {}

    private void getSummarySortedByName() {}

    private void getSummarySortedByTime() {}

    private void getSummarySortedByTotalPayment() {}
}
