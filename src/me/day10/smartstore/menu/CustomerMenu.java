package me.day10.smartstore.menu;

public class CustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final CustomerMenu INSTANCE = new CustomerMenu();
    }
    private CustomerMenu() {}
    public static CustomerMenu getInstance() { return CustomerMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
