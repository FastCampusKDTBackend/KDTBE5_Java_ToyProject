package me.day10.smartstore.menu;

public class UpdateCustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final UpdateCustomerMenu INSTANCE = new UpdateCustomerMenu();
    }
    private UpdateCustomerMenu() {}
    public static UpdateCustomerMenu getInstance() { return UpdateCustomerMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
