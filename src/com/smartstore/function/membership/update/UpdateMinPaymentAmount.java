package com.smartstore.function.membership.update;

import com.smartstore.function.membership.set.SetMembershipHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class UpdateMinPaymentAmount implements SetMembershipHandler {
    private static UpdateMinPaymentAmount instance;

    private UpdateMinPaymentAmount(){

    }

    public static UpdateMinPaymentAmount getInstance() {
        if(instance == null){
            return new UpdateMinPaymentAmount();
        }
        return instance;
    }

    @Override
    public <T> void run(T value) {
        MembershipType membershipType = (MembershipType) value;
        MembershipRequirement requirement = Memberships.getInstance().getMembershipMap().get(membershipType);

        if(requirement != null){
            setMembershipRequirement(membershipType, false, true);
            System.out.printf("Set %s Minimum Payment Amount Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Defined Yet\n", membershipType.name());
        }
    }

}
