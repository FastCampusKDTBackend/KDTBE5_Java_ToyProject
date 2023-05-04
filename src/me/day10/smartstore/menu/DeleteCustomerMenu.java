package me.day10.smartstore.menu;

public class DeleteCustomerMenu implements Menu {

    private static class InstanceHolder {
        private static final DeleteCustomerMenu INSTANCE = new DeleteCustomerMenu();
    }
    private DeleteCustomerMenu() {}
    public static DeleteCustomerMenu getInstance() { return DeleteCustomerMenu.InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        return null;
    }
}
