package com.smartstore.function.customer;

import com.smartstore.function.membership.SetMembershipRequirement;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class AddCustomer {

    private static AddCustomer instance;

    private AddCustomer(){

    }

    public static AddCustomer getInstance() {
        if(instance == null){
            return new AddCustomer();
        }
        return instance;
    }

}
