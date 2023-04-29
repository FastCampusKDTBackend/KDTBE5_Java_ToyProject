package me.day10.smartstore.menu;

public class ClassificationSummaryMenu implements Menu {

    private static class InstanceHolder {
        private static final ClassificationSummaryMenu INSTANCE = new ClassificationSummaryMenu();
    }
    private ClassificationSummaryMenu() {}
    public static ClassificationSummaryMenu getInstance() { return ClassificationSummaryMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
