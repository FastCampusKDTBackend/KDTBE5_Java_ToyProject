package com.smartstore.menu.membership;

import com.smartstore.menu.Menu;

public class MembershipMenu implements Menu {
    @Override
    public void handleChoice(int menuNumber) {


    }

    public static MembershipMenu getInstance() {
        if(instance == null){
            instance = new MembershipMenu();
        }
        return instance;
    }

    private static MembershipMenu instance;

}

