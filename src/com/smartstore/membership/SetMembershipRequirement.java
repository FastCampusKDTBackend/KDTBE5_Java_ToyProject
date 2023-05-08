package com.smartstore.membership;

import com.smartstore.menu.Menu;

public class SetMembershipRequirement implements Menu {
    @Override
    public void run() {
        //Do something
        System.out.println("Which one?");
        for(MembershipType key : MembershipType.values()){
            System.out.printf("%s | ",key.name());

        }

    }

    public SetMembershipRequirement() {

    }

    @Override
    public void handleChoice(int menuNumber) {

    }
}
