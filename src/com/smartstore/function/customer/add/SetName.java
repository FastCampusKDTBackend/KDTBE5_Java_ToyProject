package com.smartstore.function.customer.add;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;


//implement other
public class SetName implements MembershipMenuHandler {
    private static SetName instance;

    private SetName(){

    }

    public static SetName getInstance(){
        if(instance == null){
            return new SetName();
        }
        return instance;
    }


    @Override
    public void processMembership(MembershipType membershipType, MembershipRequirement requirement) {

    }
}
