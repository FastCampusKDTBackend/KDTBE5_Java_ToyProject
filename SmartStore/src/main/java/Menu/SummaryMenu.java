package Menu;

public class SummaryMenu {
    private static SummaryMenu allSummaryMenu;

    public static SummaryMenu getInstance() {
        if (allSummaryMenu == null) {
            allSummaryMenu = new SummaryMenu();
        }
        return allSummaryMenu;
    }

    private SummaryMenu() {

    }
}
