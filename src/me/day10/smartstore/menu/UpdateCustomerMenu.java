package me.day10.smartstore.menu;

public class UpdateCustomerMenu extends Menu {

    private static final UpdateCustomerMenu INSTANCE = new UpdateCustomerMenu();
    private UpdateCustomerMenu() {}
    public static UpdateCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
