package com.smartstore.function.membership.view;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class ViewMembershipRequirement implements MembershipMenuHandler {
    private static ViewMembershipRequirement instance;

    private ViewMembershipRequirement(){

    }

    public static ViewMembershipRequirement getInstance() {
        if(instance == null){
            return new ViewMembershipRequirement();
        }
        return instance;
    }

    @Override
    public <T> void run(T value) {
        MembershipType membershipType = (MembershipType) value;
        MembershipRequirement requirement = Memberships.getInstance().getMembershipMap().get(membershipType);

        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        } else{
            System.out.printf("%s Info\n", membershipType.name());
            System.out.printf("Min Usage time : %d\n",requirement.getMinUsageTime());
            System.out.printf("Min Payment Amount: %d\n\n",requirement.getMinPaymentAmount());
        }
    }
}
