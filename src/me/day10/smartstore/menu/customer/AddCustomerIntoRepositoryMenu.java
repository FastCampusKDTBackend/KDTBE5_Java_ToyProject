package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.CustomerMenu;

public class AddCustomerIntoRepositoryMenu extends Menu {

    private static class InstanceHolder {
        private static final AddCustomerIntoRepositoryMenu INSTANCE = new AddCustomerIntoRepositoryMenu();
    }
    private AddCustomerIntoRepositoryMenu() {}
    public static AddCustomerIntoRepositoryMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        setNextMenus();
        addTempCustomerIntoRepository();
        return getBackMenu();
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(null, CustomerMenu.getInstance());
    }

    private void addTempCustomerIntoRepository() {
        CustomerRepository.getInstance().addTempIntoRepository();
    }
}
