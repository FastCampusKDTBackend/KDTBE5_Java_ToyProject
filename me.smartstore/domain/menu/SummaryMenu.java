package domain.menu;

import service.SummaryService;

import java.util.Objects;

public class SummaryMenu implements Menu {

    private static final String[] MENU_ITEMS;
    private static final int MENU_ITEMS_MAX_NUM;
    private static final int MENU_ITEMS_MIN_NUM;

    private static SummaryMenu summaryMenu;
    private static SummaryService summaryService;

    static {
        MENU_ITEMS = new String[]{"Summary", "Summary (Sorted By Name)",
                "Summary (Sorted By Spent Time)", "Summary (Sorted By Total Payment)", "Back"};
        MENU_ITEMS_MAX_NUM = MENU_ITEMS.length;
        MENU_ITEMS_MIN_NUM = 1;
        summaryService = SummaryService.getInstance();
    }

    public static SummaryMenu getInstance() {
        if (Objects.isNull(summaryMenu)){
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {

    }

    @Override
    public String[] items() {
        return MENU_ITEMS;
    }

    @Override
    public int menuItemsMaxNum() {
        return MENU_ITEMS_MAX_NUM;
    }

    @Override
    public int menuItemsMinNum() {
        return MENU_ITEMS_MIN_NUM;
    }

    @Override
    public void service(int menuNum) {
        if (menuNum == 1) summaryService.summaryDefault();
        if (menuNum == 2) summaryService.summarySortedByName();
        if (menuNum == 3) summaryService.summarySortedBySpentTime();
        if (menuNum == 4) summaryService.summarySortedByTotalPayment();
    }
}
