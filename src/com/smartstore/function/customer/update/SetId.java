package com.smartstore.function.customer.update;

import com.smartstore.function.Handleable;

public class SetId implements UpdateCustomerHandler, Handleable {
    private static SetId instance;

    private SetId(){

    }

    public static SetId getInstance(){
        if(instance == null){
            return new SetId();
        }
        return instance;
    }

    @Override
    public void run() {
    }
}
