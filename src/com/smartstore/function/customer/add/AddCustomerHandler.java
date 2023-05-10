package com.smartstore.function.customer.add;

import com.smartstore.function.customer.CustomerMenuHandler;

public interface AddCustomerHandler extends CustomerMenuHandler {
    <T> void run(T value);

    @Override
    void run();
}
