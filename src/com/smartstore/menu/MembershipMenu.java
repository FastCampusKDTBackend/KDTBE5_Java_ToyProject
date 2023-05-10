package com.smartstore.menu;

import com.smartstore.function.membership.MembershipFunction;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.Function;
import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.function.menu.MainMenuFunction;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class MembershipMenu implements MenuHandler {
    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MembershipFunction.BACK.getMenuNumber()) {
            return true;
        }
        Function.of(Integer.parseInt(menuNumber), MembershipFunction.class).run();
        return false;
    }

    public static MembershipMenu getInstance() {
        if(instance == null){
            instance = new MembershipMenu();
        }
        return instance;
    }

    private static MembershipMenu instance;

}

