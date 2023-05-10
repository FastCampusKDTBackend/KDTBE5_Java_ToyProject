package com.smartstore.function.customer.delete;

import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class DeleteCustomer implements CustomerMenuHandler {
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
