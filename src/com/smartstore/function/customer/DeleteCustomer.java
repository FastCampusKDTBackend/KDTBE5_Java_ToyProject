package com.smartstore.function.customer;

import com.smartstore.function.membership.SetMembershipRequirement;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class DeleteCustomer {
    private static DeleteCustomer instance;

    private DeleteCustomer(){

    }

    public static DeleteCustomer getInstance() {
        if(instance == null){
            return new DeleteCustomer();
        }
        return instance;
    }

}
