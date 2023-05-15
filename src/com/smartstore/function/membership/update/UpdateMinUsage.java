package com.smartstore.function.membership.update;

import com.smartstore.function.membership.set.SetMembershipHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class UpdateMinUsage implements SetMembershipHandler {
    private static UpdateMinUsage instance;

    private UpdateMinUsage(){

    }

    public static UpdateMinUsage getInstance() {
        if(instance == null){
            return new UpdateMinUsage();
        }
        return instance;
    }
    @Override
    public <T> void run(T value) {
        MembershipType membershipType = (MembershipType) value;
        MembershipRequirement requirement = Memberships.getInstance().getMembershipMap().get(membershipType);

        if(requirement != null){
            setMembershipRequirement(membershipType, true, false);
            System.out.printf("Set %s Minimum Usage Successfully\n\n\n",membershipType.name());
        }else {
            System.out.printf("Membership '%s' Defined Yet\n", membershipType.name());
        }
    }
}
