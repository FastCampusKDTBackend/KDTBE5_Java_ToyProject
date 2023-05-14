package com.smartstore.function.membership.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.HandleableParam;
import com.smartstore.function.Handler;

public enum UpdateMembershipRequirementFunction implements Function {

    SET_MIN_USAGE(1, SetMinUsage.getInstance(), "Set Min Usage"),
    SET_MIN_PAYMENT_AMOUNT(2, SetMinPaymentAmount.getInstance(), "Set Min Payment Amount"),
    BACK(3, Back.getInstance(), "Return to Prev Menu");

    private final int menuNumber;
    private final HandleableParam handler;
    private String menuText;
    UpdateMembershipRequirementFunction(int menuNumber, HandleableParam handler, String menuText) {
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
