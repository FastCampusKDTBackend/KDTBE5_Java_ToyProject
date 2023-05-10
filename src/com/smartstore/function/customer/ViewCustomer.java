package com.smartstore.function.customer;

import com.smartstore.function.membership.SetMembershipRequirement;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class ViewCustomer {
    private static ViewCustomer instance;

    private ViewCustomer(){

    }

    public static ViewCustomer getInstance() {
        if(instance == null){
            return new ViewCustomer();
        }
        return instance;
    }

}
