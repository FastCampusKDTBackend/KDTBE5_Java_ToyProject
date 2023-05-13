package com.smartstore.function.customer.update;

public class SetName implements UpdateCustomerHandler {
    private static SetName instance;

    private SetName(){

    }

    public static SetName getInstance(){
        if(instance == null){
            return new SetName();
        }
        return instance;
    }

    @Override
    public void run() {
    }
}
