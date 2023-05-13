package com.smartstore.function.customer.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.Handleable;
import com.smartstore.function.membership.update.SetMinPaymentAmount;

public enum UpdateCustomerFunction implements Function {

    NAME(1, SetName.getInstance(), "Set Name"),
    ID(2, SetMinPaymentAmount.getInstance(), "Set ID"),
    USAGE_TIME(3, Back.getInstance(), "Set Usage Time"),
    PAYMENT_AMOUNT(4, Back.getInstance(), "Set Payment Amount"),
    BACK(5, Back.getInstance(), "Return to prev Menu");


    private final int menuNumber;
    private final Handleable handler;
    private final String message;
    UpdateCustomerFunction(int menuNumber, Handleable handler, String message) {
        this.menuNumber = menuNumber;
        this.handler = handler;
        this.message = message;
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
