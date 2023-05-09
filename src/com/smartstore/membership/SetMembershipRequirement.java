package com.smartstore.membership;

import com.smartstore.menu.mainmenu.MainMenuFunction;
import com.smartstore.util.Function;

public class SetMembershipRequirement implements MembershipMenuController {
    @Override
    public void run() {
        //get values from enum by string array
        String[] values = getEnumValues();
        displayMenu(values);

        runMenuSelectionLoop(values);

    }

    private void setMembershipRequirement(MembershipType membershipType) {
        Memberships.getInstance().setMembershipRequirement(membershipType);
        System.out.printf("Set %s Successfully\n\n\n",membershipType.name());
    }

    @Override
    public void handleChoice(String membershipName) {
        MembershipType membershipType = getMembershipType(membershipName);
        //find requirement using type in enum_map
        MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);
        //if not found in enum_map
        if(requirement == null ){
            setMembershipRequirement(membershipType);
        } else{
            System.out.printf("Membership '%s' Already Defined\n", membershipType.name());

        }
        //Back to prev Menu
        returnToPrevMenu();
    }

    public void returnToPrevMenu(){
        MainMenuFunction mainMenuFunction = Function.of(1, MainMenuFunction.class);
        mainMenuFunction.run();
    }
}
