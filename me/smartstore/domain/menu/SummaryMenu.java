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
        System.out.println("써머리 메뉴입니다.");
    }
}
