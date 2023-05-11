package me.smartstore.menu.summary;

import me.smartstore.summary.Summary;
import me.smartstore.menu.Menu;

public class SummaryDefaultMenu extends Menu {

    private static class InstanceHolder {
        private static final SummaryDefaultMenu INSTANCE = new SummaryDefaultMenu();
    }
    private SummaryDefaultMenu() {}
    public static SummaryDefaultMenu getInstance() { return InstanceHolder.INSTANCE; }

    @Override
    public Menu printAndInputAndGetNextMenu() {
        print(Summary.get());
        return getPrevMenu();
    }

    @Override
    protected void setNextMenus() {}
}
