package me.day10.smartstore.menu;

public class ClassificationSummaryMenu extends Menu {

    private static class InstanceHolder {
        private static final ClassificationSummaryMenu INSTANCE = new ClassificationSummaryMenu();
    }
    private ClassificationSummaryMenu(Menu... nextMenus) {
        super(nextMenus);
    }
    public static ClassificationSummaryMenu getInstance() { return ClassificationSummaryMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
