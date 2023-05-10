package com.smartstore.function.customer.update;

import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

public class UpdateCustomer implements CustomerMenuHandler {
    private static UpdateCustomer instance;

    private UpdateCustomer() {

    }

    public static UpdateCustomer getInstance() {
        if (instance == null) {
            return new UpdateCustomer();
        }
        return instance;
    }
}
