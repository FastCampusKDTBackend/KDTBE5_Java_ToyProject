package com.smartstore.function.customer.update;

import com.smartstore.customer.Customer;

public class SetUsageTime implements UpdateCustomerHandler {
    private static SetUsageTime instance;

    private SetUsageTime(){

    }

    public static SetUsageTime getInstance(){
        if(instance == null){
            return new SetUsageTime();
        }
        return instance;
    }

    @Override
    public <T> void run(T value) {
        String usageTime;
        usageTime = getValueOrEnd("Input New UsageTime\n Wait for input... 'end' to exit", Integer.class);
        if(usageTime.isBlank() || "end".equals(usageTime)){
            System.out.println("Change not saved");
            return;
        }
        ((Customer) value).setUsageTime(Integer.parseInt(usageTime));
        //update membership
        ((Customer) value).updateMembership();
        System.out.println("Change Successfully Saved");
    }
}
