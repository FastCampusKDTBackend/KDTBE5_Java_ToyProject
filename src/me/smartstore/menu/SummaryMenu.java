package me.smartstore.menu;

public class SummaryMenu implements Menu{
    private static SummaryMenu summaryMenu;

    private SummaryMenu() {
    }

    public static SummaryMenu getInstance(){
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    @Override
    public void root() {

    }
}
