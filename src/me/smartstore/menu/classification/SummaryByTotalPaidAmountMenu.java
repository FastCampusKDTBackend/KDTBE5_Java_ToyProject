package me.smartstore.menu.classification;

import me.smartstore.customer.CustomerRepository;
import me.smartstore.customer.Order;

public class SummaryByTotalPaidAmountMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryByTotalPaidAmountMenu INSTANCE = new SummaryByTotalPaidAmountMenu();
    }
    private SummaryByTotalPaidAmountMenu() {}
    public static SummaryByTotalPaidAmountMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected void setNextMenus() {}

    @Override
    protected String getSummary(Order order) {
        return CustomerRepository.getInstance().getSummaryByTotalPaidAmount(order);
    }
}
