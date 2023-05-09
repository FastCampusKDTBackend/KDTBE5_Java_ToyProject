package com.smartstore.membership;

public class ViewMembershipRequirement implements MembershipMenuController {
    public void viewMembershipRequirement(MembershipType membershipType, MembershipRequirement requirement) {
        System.out.printf("%s Info\n", membershipType.name());
        System.out.printf("Min Usage time : %d\n",requirement.getMinUsageTime());
        System.out.printf("Min Payment Amount: %d\n\n",requirement.getMinPaymentAmount());
    }

    @Override
    public void handleChoice(String membershipName) {
        MembershipType membershipType = getMembershipType(membershipName);
        //find requirement using type in enum_map
        MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);
        //if not found in enum_map
        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        } else{
            viewMembershipRequirement(membershipType, requirement);
        }
        //Back to prev Menu
        returnToPrevMenu();
    }
}
