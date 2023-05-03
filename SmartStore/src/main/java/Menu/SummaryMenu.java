package Menu;

public class SummaryMenu implements Menu{
    private static SummaryMenu allSummaryMenu;

    public static SummaryMenu getInstance() {
        if (allSummaryMenu == null) {
            allSummaryMenu = new SummaryMenu();
        }
        return allSummaryMenu;
    }

    private SummaryMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            int choice = allSummaryMenu.chooseMenu(new String[] {
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Total Payment)",
                    "Back"
            });

            if (choice == 1) summary(choice);
            else if (choice == 2) summary(choice);
            else if (choice == 3) summary(choice);
            else if (choice == 4) summary(choice);
            else if (choice == 5) break;
        }
    }

    private void summary(int choice) {
        System.out.println(choice + "summary");
    }
}
