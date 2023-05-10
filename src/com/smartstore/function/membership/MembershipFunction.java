package com.smartstore.function.membership;

import com.smartstore.function.Back;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.Function;

public enum MembershipFunction implements Function {
    SET(1, SetMembershipRequirement.getInstance()),
    VIEW(2, ViewMembershipRequirement.getInstance()),
    UPDATE(3, UpdateMembershipRequirement.getInstance()),
    BACK(4, Back.getInstance());


    private final int menuNumber;
    private final MenuHandler menuHandler;
    MembershipFunction(int menuNumber, MenuHandler menuHandler) {
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
