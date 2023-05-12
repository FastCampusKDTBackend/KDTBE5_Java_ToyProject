package me.smartstore.menu.summary;

import me.smartstore.summary.Order;
import me.smartstore.summary.SummaryBySpentHours;

public class SummaryBySpentHoursMenu extends SummaryMenu {

    private static class InstanceHolder {
        private static final SummaryBySpentHoursMenu INSTANCE = new SummaryBySpentHoursMenu();
    }
    private SummaryBySpentHoursMenu() {}
    public static SummaryBySpentHoursMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    protected String getSummary(Order order) {
        return SummaryBySpentHours.getInstance().get(order);
    }
}
