package com.smartstore.function.membership.set;

import com.smartstore.customer.Customers;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.Handleable;
import com.smartstore.util.HandleableParam;
import com.smartstore.util.Validator;

public interface SetMembershipHandler extends HandleableParam, Handleable {
    @Override
    default void run() {

    }

    default void setMembershipRequirement(MembershipType membershipType){
        setMembershipRequirement(membershipType, false, false);
    }

    default void setMembershipRequirement(MembershipType membershipType, boolean isUpdateUsageTime, boolean isUpdatePaymentAmount){
        int minUsageTime;
        int minPaymentAmount;
        boolean isValidUsage;
        boolean isValidPaymentAmount;

        while (true) {
            minUsageTime = isUpdateUsageTime ? Validator.getInteger("Input minUsageTime : ") : Memberships.getInstance().findByType(membershipType).getMinUsageTime();
            isValidUsage = Validator.isValidMinUsage(membershipType, minUsageTime);
            minPaymentAmount = isUpdatePaymentAmount ? Validator.getInteger("Input minPaymentAmount : ") : Memberships.getInstance().findByType(membershipType).getMinPaymentAmount();
            isValidPaymentAmount = Validator.isValidMinPayment(membershipType, minPaymentAmount);

            if(membershipType != MembershipType.values()[0] && membershipType != MembershipType.values()[1]){
                //if current memberShip requirement equals prevMembership requirement -> false
                MembershipRequirement prevRequirement = Memberships.getInstance().findByType(MembershipType.values()[membershipType.ordinal()-1]);
                if(minUsageTime == prevRequirement.getMinUsageTime() && minPaymentAmount == prevRequirement.getMinPaymentAmount()) {
                    System.out.println("Usage Time & Payment Amount Can't be Same of Lower Membership");
                    isValidUsage = false;
                    isValidUsage = false;
                }
            }
            /*
                    isValidUsageTime -> prev Membership's minUsageTime <= minUsageTime
                    isValidPaymentAmount -> prev Membership's minPaymentAmount <= minPaymentAmount
                    isValidUsage & isValidPaymentAmount Must Satisfy the AND Operation

                    ex) set VIP
                        General (1,5), VIP(2,9) -> T,T OK
                        General (1,5), VIP(1,6) -> T,T OK
                        General (1,3), VIP(1,3) -> T,T but not satisfy condition(equals not allowed)
                        General (1,3), VIP(1,1) -> T,F Fail
                        General (1,1), VIP(0,4) -> F,T Fail
                        General (1,3), VIP(0,0) -> F,F Fail
                */
            if (isValidUsage && isValidPaymentAmount) {
                break;
            }
            System.out.println("Check Input Data Again");

            System.out.printf("isValidUsage & isValidPaymentAmount Must Satisfy the AND Operation. Current Situation : %s, %s\n",isValidUsage, isValidPaymentAmount);
        }
        setMembershipRequirement(membershipType, minUsageTime, minPaymentAmount);
        // TODO: 2023-05-15 check minUsage, minPayment Bigger than prev Membership
    }

    default void setMembershipRequirement(MembershipType membershipType, int minUsage, int minPaymentAmount){
        Memberships.getInstance().getMembershipMap().put(membershipType, new MembershipRequirement(minUsage, minPaymentAmount));
        //update membership
        Customers.getInstance().updateMembership();
    }
}
