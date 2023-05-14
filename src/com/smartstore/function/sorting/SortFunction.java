package com.smartstore.function.sorting;

import com.smartstore.function.Back;
import com.smartstore.util.Function;
import com.smartstore.util.Handleable;

public enum SortFunction implements Function {

    BY_MEMBERSHIP(1, SortByName.getInstance(), "Summary GroupBy Membership"),
    BY_NAME(2, SortByName.getInstance(), "Summary GroupBy Membership[Name]"),
    BY_USAGE_TIME(3, SortByName.getInstance(), "Summary GroupBy Membership[Usage Time]"),
    BY_PAYMENT_AMOUNT(4, SortByName.getInstance(), "Summary GroupBy Membership[Payment Amount]"),
    BACK(5, Back.getInstance(), "Return to prev Menu");


    private final int menuNumber;
    private final Handleable handler;
    private final String menuText;
    SortFunction(int menuNumber, Handleable handler, String menuText) {
        this.menuNumber = menuNumber;
        this.handler = handler;
        this.menuText = menuText;
    }

    @Override
    public String getMenuText() {
        return menuText;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public Handleable getMenuHandler() {
        return this.handler;
    }

}
