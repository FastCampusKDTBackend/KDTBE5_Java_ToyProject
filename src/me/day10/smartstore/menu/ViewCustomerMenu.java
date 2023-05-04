package me.day10.smartstore.menu;

public class ViewCustomerMenu extends Menu {

    private static final ViewCustomerMenu INSTANCE = new ViewCustomerMenu();
    private ViewCustomerMenu() {}
    public static ViewCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
