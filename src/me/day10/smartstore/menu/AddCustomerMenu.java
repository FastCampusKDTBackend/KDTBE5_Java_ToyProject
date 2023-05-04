package me.day10.smartstore.menu;

public class AddCustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final AddCustomerMenu INSTANCE = new AddCustomerMenu();
    }
    private AddCustomerMenu() {}
    public static AddCustomerMenu getInstance() { return AddCustomerMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
