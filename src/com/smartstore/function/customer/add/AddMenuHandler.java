package com.smartstore.function.customer.add;

import com.smartstore.function.customer.CustomerMenuHandler;

public interface AddMenuHandler extends CustomerMenuHandler {
    <T> void run(T value);

    @Override
    void run();
}
