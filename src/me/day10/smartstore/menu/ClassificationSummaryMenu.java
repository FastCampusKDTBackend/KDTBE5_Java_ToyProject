package me.day10.smartstore.menu;

public class ClassificationSummaryMenu extends TopicIntroMenu {

    private static final String SUMMARY_MENU_OUTPUT = null;
    private static final ClassificationSummaryMenu INSTANCE = new ClassificationSummaryMenu(SUMMARY_MENU_OUTPUT);

    private ClassificationSummaryMenu(String TOPIC_OUTPUT, Menu... nextMenus) {
        super(TOPIC_OUTPUT, nextMenus);
    }

    public static ClassificationSummaryMenu getInstance() { return INSTANCE; }
}
