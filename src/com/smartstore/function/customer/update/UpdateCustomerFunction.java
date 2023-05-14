package com.smartstore.function.customer.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.Handleable;
import com.smartstore.function.HandleableParam;
import com.smartstore.function.membership.update.SetMinPaymentAmount;

public enum UpdateCustomerFunction implements Function {

    NAME(1, SetName.getInstance(), "Set Name"),
    ID(2, SetId.getInstance(), "Set ID"),
    USAGE_TIME(3, SetUsageTime.getInstance(), "Set Usage Time"),
    PAYMENT_AMOUNT(4, SetPaymentAmount.getInstance(), "Set Payment Amount"),
    BACK(5, Back.getInstance(), "Return to prev Menu");


    private final int menuNumber;
    private final HandleableParam handler;
    private final String menuText;
    UpdateCustomerFunction(int menuNumber, HandleableParam handler, String menuText) {
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
    public HandleableParam getMenuHandler() {
        return this.handler;
    }

}
