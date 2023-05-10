package com.smartstore.function.customer;

import com.smartstore.function.membership.SetMembershipRequirement;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class UpdateCustomer {
    private static UpdateCustomer instance;

    private UpdateCustomer(){

    }

    public static UpdateCustomer getInstance() {
        if(instance == null){
            return new UpdateCustomer();
        }
        return instance;
    }

}
