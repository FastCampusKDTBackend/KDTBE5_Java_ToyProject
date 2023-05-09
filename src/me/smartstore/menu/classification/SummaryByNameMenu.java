package me.smartstore.menu.classification;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.Order;

public class SummaryByNameMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryByNameMenu INSTANCE = new SummaryByNameMenu();
    }
    private SummaryByNameMenu() {}
    public static SummaryByNameMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    @Override
    protected String getSummary(Order order) {
        return CustomerRepository.getInstance().getSummaryByName(order);
    }
}
