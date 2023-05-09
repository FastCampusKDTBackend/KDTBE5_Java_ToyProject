package com.smartstore.membership;

import com.smartstore.menu.Menu;

public class UpdateMembershipRequirement extends SetMembershipRequirement implements Menu {


    @Override
    public void handleChoice(String membershipName) {
        MembershipType membershipType = getMembershipType(membershipName);
        //find requirement using type in enum_map
        MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);
        //if not found in enum_map
        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        } else{
            updateMembershipRequirement(membershipType, requirement);
        }
        //Back to prev Menu
        super.returnToPrevMenu();
    }

    private void updateMembershipRequirement(MembershipType membershipType, MembershipRequirement requirement){
        System.out.printf("Current %s Info\n", membershipType.name());
        System.out.printf("Min Usage time : %d\n",requirement.getMinUsageTime());
        System.out.printf("Min Payment Amount: %d\n\n",requirement.getMinPaymentAmount());
        Memberships.getInstance().setMembershipRequirement(membershipType);
        System.out.printf("Set %s Successfully\n\n\n",membershipType.name());
    }
}
