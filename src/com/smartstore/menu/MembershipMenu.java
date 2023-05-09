package com.smartstore.menu;

import com.smartstore.function.membership.MembershipFunction;
import com.smartstore.function.MenuController;
import com.smartstore.function.Function;

public class MembershipMenu implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {
        Function.of(Integer.parseInt(menuNumber), MembershipFunction.class).run();
    }

    @Override
    public void run() {

    }

    public static MembershipMenu getInstance() {
        if(instance == null){
            instance = new MembershipMenu();
        }
        return instance;
    }

    private static MembershipMenu instance;

}

