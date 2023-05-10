package com.smartstore.function.membership;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.membership.set.SetMembershipRequirement;
import com.smartstore.function.membership.update.UpdateMembershipRequirement;
import com.smartstore.function.membership.view.ViewMembershipRequirement;

public enum MembershipFunction implements Function {
    SET(1, SetMembershipRequirement.getInstance()),
    VIEW(2, ViewMembershipRequirement.getInstance()),
    UPDATE(3, UpdateMembershipRequirement.getInstance()),
    BACK(4, Back.getInstance());


    private final int menuNumber;
    private final MenuHandler membershipMenuHandler;
    MembershipFunction(int menuNumber, MenuHandler membershipMenuHandler) {
        this.menuNumber = menuNumber;
        this.membershipMenuHandler = membershipMenuHandler;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public MenuHandler getMenuController() {
        return this.membershipMenuHandler;
    }

}
