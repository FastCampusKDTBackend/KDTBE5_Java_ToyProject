package com.smartstore.function.membership.set;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMembershipRequirement implements MembershipMenuHandler {
    private static SetMembershipRequirement instance;

    private SetMembershipRequirement(){

    }

    public static SetMembershipRequirement getInstance() {
        if(instance == null){
            return new SetMembershipRequirement();
        }
        return instance;
    }

    @Override
    public void processMembership(MembershipType membershipType, MembershipRequirement requirement) {
        if(requirement == null){
            Memberships.getInstance().setMembershipRequirement(membershipType);
            System.out.printf("Set %s Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Already Defined\n", membershipType.name());
        }
    }

}
