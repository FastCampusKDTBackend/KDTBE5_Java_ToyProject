package com.smartstore.membership;

import com.smartstore.util.CustomList;

public class SetMembershipRequirement implements MembershipMenuController {
    @Override
    public void run() {
        //Do something
        CustomList<String> keyList = new CustomList<>();
        for(MembershipType membershipType : MembershipType.values()){
            keyList.add(membershipType.name());
        }
        String[] keyArray = keyList.toArray(String[].class);
        displayMenu(keyArray);

        runMenuSelectionLoop(keyArray);

    }

    public SetMembershipRequirement() {

    }

    @Override
    public void handleChoice(String menu) {

    }
}
