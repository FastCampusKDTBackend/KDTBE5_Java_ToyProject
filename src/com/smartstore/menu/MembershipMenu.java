package com.smartstore.menu;

import com.smartstore.function.membership.MembershipFunction;
import com.smartstore.function.mainmenu.MainMenuHandler;
import com.smartstore.function.Function;

public class MembershipMenu implements MainMenuHandler {
    private static MembershipMenu instance;
    public static MembershipMenu getInstance() {
        if(instance == null){
            instance = new MembershipMenu();
        }
        return instance;
    }
    private MembershipMenu(){

    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MembershipFunction.BACK.getMenuNumber()) {
            return true;
        }
        Function.of(Integer.parseInt(menuNumber), MembershipFunction.class).run();
        return false;
    }

}

