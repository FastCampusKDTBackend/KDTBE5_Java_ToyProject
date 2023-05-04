package me.day10.smartstore.menu;

public class ViewCustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final ViewCustomerMenu INSTANCE = new ViewCustomerMenu();
    }
    private ViewCustomerMenu() {}
    public static ViewCustomerMenu getInstance() { return ViewCustomerMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
