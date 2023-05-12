package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.CustomerIntroMenu;

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
        setNextMenus(CustomerIntroMenu.getInstance());
    }

    private void addTempCustomerIntoRepository() {
        CustomerRepository.getInstance().addTempIntoRepository();
    }
}
