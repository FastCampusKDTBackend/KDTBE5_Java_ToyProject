package com.smartstore.function.membership.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.Handler;

public enum UpdateMembershipRequirementFunction implements Function {

    SET_MIN_USAGE(1, SetMinUsage.getInstance()),
    SET_MIN_PAYMENT_AMOUNT(2, SetMinPaymentAmount.getInstance()),
    BACK(3, Back.getInstance());

    private final int menuNumber;
    private final Handler mainMenuHandler;
    UpdateMembershipRequirementFunction(int menuNumber, Handler mainMenuHandler) {
        this.menuNumber = menuNumber;
        this.mainMenuHandler = mainMenuHandler;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    public void run(int ordinal){
        getMenuHandler().run(ordinal);
    }

    @Override
    public Handler getMenuHandler() {
        return this.mainMenuHandler;
    }

}
