package me.smartstore.menu.customer;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.CustomerMenu;

public class ViewCustomerMenu extends Menu {

    private static class InstanceHolder {
        private static final ViewCustomerMenu INSTANCE = new ViewCustomerMenu();
    }
    private ViewCustomerMenu() {}
    public static ViewCustomerMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        print(CustomerRepository.getInstance());
        setNextMenus();
        return getBackMenu();
    }

    @Override
    protected void setNextMenus() {
        setNextMenus(CustomerMenu.getInstance());
    }
}
