package me.smartstore.menu.classification;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.Order;

public class SummaryBySpentHoursMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryBySpentHoursMenu INSTANCE = new SummaryBySpentHoursMenu();
    }
    private SummaryBySpentHoursMenu() {}
    public static SummaryBySpentHoursMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    @Override
    protected String getSummary(Order order) {
        return CustomerRepository.getInstance().getSummaryBySpentHours(order);
    }
}
