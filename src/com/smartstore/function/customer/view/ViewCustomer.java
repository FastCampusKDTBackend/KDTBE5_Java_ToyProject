package com.smartstore.function.customer.view;

import com.smartstore.function.customer.CustomerMenuHandler;

public class ViewCustomer implements CustomerMenuHandler {
    private static ViewCustomer instance;

    private ViewCustomer(){

    }

    public static ViewCustomer getInstance() {
        if (instance == null) {
            return new ViewCustomer();
        }
        return instance;
    }
}
