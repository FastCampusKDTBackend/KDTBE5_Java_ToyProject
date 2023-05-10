package com.smartstore.function.customer;

import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.Back;

public enum CustomerFunction implements Function {
    ADD(1, Back.getInstance()),
    VIEW(2, Back.getInstance()),
    UPDATE(3, Back.getInstance()),
    DELETE(3, Back.getInstance()),
    BACK(5, Back.getInstance());


    private final int menuNumber;
    private final MenuHandler menuHandler;
    CustomerFunction(int menuNumber, MenuHandler menuHandler) {
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
