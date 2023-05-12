package me.smartstore.menu.summary;

import me.smartstore.summary.Order;
import me.smartstore.summary.SummaryByTotalPaidAmount;

public class SummaryByTotalPaidAmountMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryByTotalPaidAmountMenu INSTANCE = new SummaryByTotalPaidAmountMenu();
    }
    private SummaryByTotalPaidAmountMenu() {}
    public static SummaryByTotalPaidAmountMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected String getSummary(Order order) {
        return SummaryByTotalPaidAmount.getInstance().get(order);
    }
}
