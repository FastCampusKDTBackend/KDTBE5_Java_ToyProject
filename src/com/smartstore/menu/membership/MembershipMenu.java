package com.smartstore.menu.membership;

import com.smartstore.membership.MembershipFunction;
import com.smartstore.menu.Menu;
import com.smartstore.util.Function;

public class MembershipMenu implements Menu {
    @Override
    public void handleChoice(int menuNumber) {
        Function.of(menuNumber, MembershipFunction.class).run();
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

