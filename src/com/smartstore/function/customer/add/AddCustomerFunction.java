package com.smartstore.function.customer.add;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.function.mainmenu.MainMenuHandler;
import com.smartstore.function.membership.update.SetMinPaymentAmount;

public enum AddCustomerFunction implements Function {

    NAME(1, SetName.getInstance()),
    ID(2, SetMinPaymentAmount.getInstance()),
    USAGE_TIME(3, Back.getInstance()),
    PAYMENT_AMOUNT(4, Back.getInstance()),
    BACK(5, Back.getInstance());


    private final int menuNumber;
    private final MenuHandler mainMenuHandler;
    AddCustomerFunction(int menuNumber, MenuHandler mainMenuHandler) {
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
