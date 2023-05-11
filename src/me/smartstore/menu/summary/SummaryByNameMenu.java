package me.smartstore.menu.summary;

import me.smartstore.summary.Order;
import me.smartstore.summary.SummaryByName;

public class SummaryByNameMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryByNameMenu INSTANCE = new SummaryByNameMenu();
    }
    private SummaryByNameMenu() {}
    public static SummaryByNameMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected String getSummary(Order order) {
        return SummaryByName.getInstance().get(order);
    }
}
