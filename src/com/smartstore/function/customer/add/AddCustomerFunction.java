package com.smartstore.function.customer.add;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.membership.update.SetMinPaymentAmount;
import com.smartstore.function.membership.update.SetMinUsage;

public enum AddCustomerFunction implements Function {

    NAME(1, SetName.getInstance()),
    ID(2, SetMinPaymentAmount.getInstance()),
    USAGE_TIME(3, Back.getInstance()),
    PAYMENT_AMOUNT(4, Back.getInstance()),
    BACK(5, Back.getInstance());


    private final int menuNumber;
    private final MenuHandler menuHandler;
    AddCustomerFunction(int menuNumber, MenuHandler menuHandler) {
        this.menuNumber = menuNumber;
        this.menuHandler = menuHandler;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public MenuHandler getMenuController() {
        return this.menuHandler;
    }

}
