package com.smartstore.function.membership.update;

import com.smartstore.util.HandleableParam;
import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMinPaymentAmount implements MembershipMenuHandler, HandleableParam {
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
            Memberships.getInstance().setMembershipRequirement(membershipType, requirement.getMinUsageTime(), Memberships.getInstance().getMinPaymentAmountFromInput());
            System.out.printf("Set %s Minimum Payment Amount Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Defined Yet\n", membershipType.name());
        }
    }

    @Override
    public <T> void run(T value) {
        if(value.getClass().getName() != null){
            processMembership((MembershipType) value, Memberships.getInstance().findByType((MembershipType) value));
        }
    }

}
