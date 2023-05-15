package jyjang.smartstore.menu;

public class SummarizedMenu implements Menu {

    private static SummarizedMenu summarizedMenu;

    public static SummarizedMenu getInstance() {
        if (summarizedMenu == null)
            summarizedMenu = new SummarizedMenu();
        return summarizedMenu;
    }

    private SummarizedMenu() {

    }

    @Override
    public void manage() {
        while (true) {
            int choice = displayMenu(new String[] {
                    "Summary"
                    , "Summary (Sorted By Name)"
                    , "Summary (Sorted By Time)"
                    , "Summary (Sorted By Pay)"
                    , "Back"});
        }
    }
}
