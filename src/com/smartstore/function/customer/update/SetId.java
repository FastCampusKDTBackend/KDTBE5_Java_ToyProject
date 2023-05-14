package com.smartstore.function.customer.update;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.HandleableParam;
import com.smartstore.function.IdDuplicateChecker;
import com.smartstore.function.ValueWithEndValidator;
import com.smartstore.util.CustomList;

public class SetId implements UpdateCustomerHandler {
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
    public <T> void run(T value) {
        String id;
        while (true){
            id = getValueOrEnd("Input New Id\n Wait for input... 'end' to exit", String.class);
            if(id.isBlank() || "end".equals(id)){
                System.out.println("Change not saved");
                break;
            }
            if(!isIdDuplicated(id)){
                System.out.println("Change Successfully Saved");
                ((Customer) value).setCustomerId(id);
                break;
            }
            System.out.printf("User Id %s already Exist Try Other Id\n", id);
        }
    }
}
