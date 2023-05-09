package com.smartstore.function.membership;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMembershipRequirement implements MembershipMenuController {
    @Override
    public void run(MembershipType membershipType, MembershipRequirement requirement) {
        if(requirement == null){
            Memberships.getInstance().setMembershipRequirement(membershipType);
            System.out.printf("Set %s Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Already Defined\n", membershipType.name());
        }
    }

}
