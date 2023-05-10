package com.smartstore.function.membership.update;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.mainmenu.MainMenuHandler;

public enum UpdateMembershipRequirementFunction implements Function {

    SET_MIN_USAGE(1, SetMinUsage.getInstance()),
    SET_MIN_PAYMENT_AMOUNT(2, SetMinPaymentAmount.getInstance()),
    BACK(3, Back.getInstance());

    private final int menuNumber;
    private final MenuHandler mainMenuHandler;
    UpdateMembershipRequirementFunction(int menuNumber, MenuHandler mainMenuHandler) {
        this.menuNumber = menuNumber;
        this.mainMenuHandler = mainMenuHandler;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public MenuHandler getMenuController() {
        return this.mainMenuHandler;
    }

}
