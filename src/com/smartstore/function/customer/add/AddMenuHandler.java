package com.smartstore.function.customer.add;

import com.smartstore.function.FunctionHandler;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.CustomList;

import java.io.IOException;

public interface AddMenuHandler extends CustomerMenuHandler {
    <T> void run(T value);

    @Override
    void run();
}
