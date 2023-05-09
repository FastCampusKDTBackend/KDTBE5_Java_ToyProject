package com.smartstore.function.membership;

import com.smartstore.function.menu.Back;
import com.smartstore.function.MenuController;
import com.smartstore.function.Function;

public enum MembershipFunction implements Function {
    SET(1, new SetMembershipRequirement()),
    VIEW(2, new ViewMembershipRequirement()),
    UPDATE(3,new UpdateMembershipRequirement()),
    BACK(4, new Back());


    private final int menuNumber;
    private final MenuController menuController;
    MembershipFunction(int menuNumber, MenuController menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public MenuController getMenuController() {
        return this.menuController;
    }

}
