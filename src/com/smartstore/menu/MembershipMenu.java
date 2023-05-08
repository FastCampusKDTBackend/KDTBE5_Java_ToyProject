package com.smartstore.menu;

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

