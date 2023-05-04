package me.day10.smartstore.menu;

public class ClassificationSummaryMenu extends Menu {

    private static final ClassificationSummaryMenu INSTANCE = new ClassificationSummaryMenu();
    private ClassificationSummaryMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static ClassificationSummaryMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
