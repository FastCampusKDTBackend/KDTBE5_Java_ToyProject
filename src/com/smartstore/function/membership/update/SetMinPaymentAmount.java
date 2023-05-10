package com.smartstore.function.membership.update;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMinPaymentAmount implements MembershipMenuHandler {
    private static SetMinPaymentAmount instance;

    private SetMinPaymentAmount(){

    }

    public static SetMinPaymentAmount getInstance() {
        if(instance == null){
            return new SetMinPaymentAmount();
        }
        return instance;
    }
    @Override
    public void processMembership(MembershipType membershipType, MembershipRequirement requirement) {
        if(requirement != null){
            Memberships.getInstance().setMembershipRequirement(membershipType, requirement.getMinUsageTime(), Memberships.getInstance().setMinPaymentAmount());
            System.out.printf("Set %s Minimum Payment Amount Successfully\n\n\n",membershipType.name());
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
