package com.smartstore.function.membership;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class UpdateMembershipRequirement implements MembershipMenuController {
    @Override
    public void run(MembershipType membershipType, MembershipRequirement requirement){
        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        } else{
            System.out.printf("Current %s Info\n", membershipType.name());
            System.out.printf("Min Usage time : %d\n", requirement.getMinUsageTime());
            System.out.printf("Min Payment Amount: %d\n\n", requirement.getMinPaymentAmount());
            //set will rewrite value if already exist
            Memberships.getInstance().setMembershipRequirement(membershipType);
            System.out.printf("Set %s Successfully\n\n\n", membershipType.name());
        }
    }
}
