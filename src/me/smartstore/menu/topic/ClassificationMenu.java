package me.smartstore.menu.topic;

import me.smartstore.menu.classification.*;

public class ClassificationMenu extends TopicIntroMenu {

    private static final String SUMMARY_MENU_OUTPUT =
                            '\n' +
                            "===== Classification Menu ====" + '\n' +
                            " 1. " + "Summary " + '\n' +
                            " 2. " + "Summary " + "(Sorted By " + "Name" + ")\n" +
                            " 3. " + "Summary " + "(Sorted By " + "Spent Hours" + ")\n" +
                            " 4. " + "Summary " + "(Sorted By " + "Total Paid Amount" + ")\n" +
                            " 5. " + "Back" + '\n' +
                            "==============================" + '\n' +
                            "Choose One: ";
    private static final ClassificationMenu INSTANCE = new ClassificationMenu();

    private ClassificationMenu() { super(SUMMARY_MENU_OUTPUT); }

    public static ClassificationMenu getInstance() { return INSTANCE; }

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
