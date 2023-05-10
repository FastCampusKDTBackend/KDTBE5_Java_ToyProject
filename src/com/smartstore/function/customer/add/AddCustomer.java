package com.smartstore.function.customer.add;

import com.smartstore.function.Function;
import com.smartstore.function.IntegerValidator;
import com.smartstore.function.customer.CustomerMenuHandler;

import java.io.IOException;

public class AddCustomer implements CustomerMenuHandler, IntegerValidator {

    private static AddCustomer instance;

    private AddCustomer(){

    }

    public static AddCustomer getInstance() {
        if(instance == null){
            return new AddCustomer();
        }
        return instance;
    }

    public void displayMenu() {
        System.out.println("How many Customers to Input ? | type 'end' to cancel");
    }
    @Override
    public boolean handleChoice(String numberOfUser) {
        if(!"end".equals(numberOfUser)){
            int count = 1;
            for(int i = 0 ; i < Integer.parseInt(numberOfUser) ; i++){

                //Function.of(count, AddCustomerFunction.class);
            }
        }
        return true;
    }

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            displayMenu();

            isExit = handleChoice(String.valueOf(getIntegerValue()));
        }
    }

    @Override
    public int getCurrentMenuNumber() {
        return 0;
    }
}
