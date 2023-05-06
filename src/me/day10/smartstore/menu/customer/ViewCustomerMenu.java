package me.day10.smartstore.menu.customer;

import me.day10.smartstore.customer.CustomerRepository;
import me.day10.smartstore.menu.Menu;
import me.day10.smartstore.menu.topic.CustomerMenu;

public class ViewCustomerMenu extends Menu {

    private static final ViewCustomerMenu INSTANCE = new ViewCustomerMenu();
    private ViewCustomerMenu() {}
    public static ViewCustomerMenu getInstance() { return INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        print(CustomerRepository.getInstance());

        return CustomerMenu.getInstance();
    }
}
