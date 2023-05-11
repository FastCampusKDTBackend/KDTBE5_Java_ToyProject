package me.smartstore.menu.topic;

import me.smartstore.menu.summary.*;

public class SummaryIntroMenu extends TopicIntroMenu {

    private static final String SUMMARY_MENU_OUTPUT =
                            '\n' +
                            "========= Summary Menu =======" + '\n' +
                            " 1. " + "Summary " + '\n' +
                            " 2. " + "Summary " + "(Sorted By " + "Name" + ")\n" +
                            " 3. " + "Summary " + "(Sorted By " + "Spent Hours" + ")\n" +
                            " 4. " + "Summary " + "(Sorted By " + "Total Paid Amount" + ")\n" +
                            " 5. " + "Back" + '\n' +
                            "==============================" + '\n' +
                            "Choose One: ";
    private static final SummaryIntroMenu INSTANCE = new SummaryIntroMenu();

    private SummaryIntroMenu() { super(SUMMARY_MENU_OUTPUT); }

    public static SummaryIntroMenu getInstance() { return INSTANCE; }

    @Override
    protected void setNextMenus() {
        setNextMenus(
                SummaryDefaultMenu.getInstance(),
                SummaryByNameMenu.getInstance(),
                SummaryBySpentHoursMenu.getInstance(),
                SummaryByTotalPaidAmountMenu.getInstance(),
                StartMenu.getInstance()
        );
    }
}
