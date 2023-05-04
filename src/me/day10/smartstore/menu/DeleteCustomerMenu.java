package me.day10.smartstore.menu;

public class DeleteCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final DeleteCustomerMenu INSTANCE = new DeleteCustomerMenu();
    }
    private DeleteCustomerMenu() {}
    public static DeleteCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
