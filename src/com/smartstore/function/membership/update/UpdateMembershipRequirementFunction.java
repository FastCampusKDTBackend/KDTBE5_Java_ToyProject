package com.smartstore.function.membership.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;

public enum UpdateMembershipRequirementFunction implements Function {

    SET_MIN_USAGE(1, SetMinUsage.getInstance()),
    SET_MIN_PAYMENT_AMOUNT(2, SetMinPaymentAmount.getInstance()),
    BACK(3, Back.getInstance());

    private final int menuNumber;
    private final MenuHandler menuHandler;
    UpdateMembershipRequirementFunction(int menuNumber, MenuHandler menuHandler) {
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
