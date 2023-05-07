package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.CustomerMenu;

public class UpdateCustomerIntoRepositoryMenu extends Menu {

    private static class InstanceHolder {
        private static final UpdateCustomerIntoRepositoryMenu INSTANCE = new UpdateCustomerIntoRepositoryMenu();
    }
    private UpdateCustomerIntoRepositoryMenu() {}
    public static UpdateCustomerIntoRepositoryMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        updateTempCustomerInRepository();
        return getBackMenu();
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, CustomerMenu.getInstance());
    }

    private void updateTempCustomerInRepository() {
        CustomerRepository.getInstance().updateTempInRepository();
    }
}
