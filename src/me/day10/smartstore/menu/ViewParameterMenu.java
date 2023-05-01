package me.day10.smartstore.menu;

public class ViewParameterMenu implements Menu {

    private static class InstanceHolder {
        private static final ViewParameterMenu INSTANCE = new ViewParameterMenu();
    }
    private ViewParameterMenu() {}
    public static ViewParameterMenu getInstance() { return ViewParameterMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
