package me.smartstore.menu.classification;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.Order;
import me.smartstore.menu.Menu;
import me.smartstore.menu.topic.CustomerMenu;

public class SummaryDefaultMenu extends Menu {

    private static class InstanceHolder {
        private static final SummaryDefaultMenu INSTANCE = new SummaryDefaultMenu();
    }
    private SummaryDefaultMenu() {}
    public static SummaryDefaultMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        print(CustomerRepository.getInstance().getSummary());
        return getPrevMenu();
    }

    @Override
    protected void setNextMenus() {}
}
