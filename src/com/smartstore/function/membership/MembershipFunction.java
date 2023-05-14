package com.smartstore.function.membership;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.Handleable;
import com.smartstore.function.Handler;
import com.smartstore.function.membership.set.SetMembershipRequirement;
import com.smartstore.function.membership.update.UpdateMembershipRequirement;
import com.smartstore.function.membership.view.ViewMembershipRequirement;

public enum MembershipFunction implements Function {
    SET(1, SetMembershipRequirement.getInstance(), "Set Membership Requirement"),
    VIEW(2, ViewMembershipRequirement.getInstance(), "View Membership Requirement"),
    UPDATE(3, UpdateMembershipRequirement.getInstance(), "Update Membership Requirement"),
    BACK(4, Back.getInstance(), "Return to Prev Menu");


    private final int menuNumber;
    private final Handleable handler;
    private String menuText;
    MembershipFunction(int menuNumber, Handleable handler, String menuText) {
        this.menuNumber = menuNumber;
        this.handler = handler;
        this.menuText = menuText;
    }


    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
        public Handleable getMenuHandler() {
        return this.handler;
    }

    @Override
    public String getMenuText() {
        return this.menuText;
    }
}
