package com.smartstore.function.membership;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMinUsage implements MembershipMenuHandler {
    private static SetMinUsage instance;

    private SetMinUsage(){

    }

    public static SetMinUsage getInstance() {
        if(instance == null){
            return new SetMinUsage();
        }
        return instance;
    }
    @Override
    public void run(MembershipType membershipType, MembershipRequirement requirement) {
        if(requirement != null){
            Memberships.getInstance().setMembershipRequirement(membershipType, Memberships.getInstance().setMinUsage(membershipType), requirement.getMinPaymentAmount());
            System.out.printf("Set %s Minimum Usage Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Defined Yet\n", membershipType.name());
        }
    }

}
