package com.smartstore.function.membership.set;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public class SetMembershipRequirement implements MembershipMenuHandler, SetMembershipHandler {
    private static SetMembershipRequirement instance;

    private SetMembershipRequirement(){

    }

    public static SetMembershipRequirement getInstance() {
        if(instance == null){
            return new SetMembershipRequirement();
        }
        return instance;
    }

    boolean isPrevMembershipExist(MembershipType membershipType){
        if(membershipType == MembershipType.values()[0] || membershipType == MembershipType.values()[1]){
            return true;
        } else{
            return Memberships.getInstance().findByType(MembershipType.values()[membershipType.ordinal() - 1]) != null;
        }
    }

    @Override
    public <T> void run(T value) {
        MembershipType membershipType = (MembershipType) value;
        MembershipRequirement requirement = Memberships.getInstance().getMembershipMap().get(membershipType);
        if(requirement == null){
            if(isPrevMembershipExist(membershipType)){
                setMembershipRequirement(membershipType,true,true);
                System.out.printf("Set %s Successfully\n\n\n",membershipType.name());
            }
            else {
                System.out.printf("Define %s first to right Sort\n", MembershipType.values()[membershipType.ordinal()-1]);
            }

        }else {
            System.out.printf("Membership '%s' Already Defined\n", membershipType.name());
        }
    }

    @Override
    public void run() {
        MembershipMenuHandler.super.run();
    }
}
