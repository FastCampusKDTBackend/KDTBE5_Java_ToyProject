package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.CustomerIntroMenu;

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
        setNextMenus(CustomerIntroMenu.getInstance());
    }

    private void updateTempCustomerInRepository() {
        CustomerRepository.getInstance().updateTempInRepository();
    }
}
