package com.smartstore.function.membership.update;

import com.smartstore.function.IntegerValidator;
import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMinUsage implements MembershipMenuHandler, IntegerValidator {
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
    public void processMembership(MembershipType membershipType, MembershipRequirement requirement) {
        if(requirement != null){
            Memberships.getInstance().setMembershipRequirement(membershipType, Memberships.getInstance().setMinUsage(membershipType), requirement.getMinPaymentAmount());
            System.out.printf("Set %s Minimum Usage Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Defined Yet\n", membershipType.name());
        }
    }

    @Override
    public void run(int ordinal) {
        MembershipType membershipType = null;
        for(MembershipType membership :MembershipType.values()){
            if(membership.ordinal() == ordinal){
                membershipType = membership;
                break;
            }
        }
        if(membershipType.name() != null){
            processMembership(membershipType, Memberships.getInstance().findByType(membershipType));
        }
    }
}
