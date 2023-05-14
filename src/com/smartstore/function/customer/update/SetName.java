package com.smartstore.function.customer.update;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.util.CustomList;

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
    public <T> void run(T value) {
        String name = "";
        Customer customer = (Customer) value;
        name = getValueOrEnd("Input New Name\n Wait for input... 'end' to exit", String.class);
        if(name.isBlank() || "end".equals(name)){
            System.out.println("Change not saved");
            return;
        }
        customer.setCustomerName(name);
        System.out.println("Change Successfully Saved");
    }
}
