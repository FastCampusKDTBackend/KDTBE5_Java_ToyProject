package com.smartstore.function.customer.add;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.function.customer.CustomerMenuHandler;

import java.io.IOException;
import java.util.Arrays;

public class AddCustomer implements CustomerMenuHandler, MenuPrintable, IntegerWithEndValidator,UserDataValidator {

    private static AddCustomer instance;

    private AddCustomer(){

    }

    public static AddCustomer getInstance() {
        if(instance == null){
            return new AddCustomer();
        }
        return instance;
    }

    @Override
    public boolean handleChoice(String numberOfUser) {
        if(!"end".equalsIgnoreCase(numberOfUser)){
            String name = "";
            String id = "";
            int usageTime = 0;
            int paymentAmount = 0;
            Customers customers = Customers.getInstance();
            //get name & id from user, usage_time&payment_amount is optional
            for(int i = 0 ; i < Integer.parseInt(numberOfUser) ; i++){
                name = getUserData(name.getClass(),true, "Input User Name : ");
                id = getUserData(id.getClass(),true, "Input User ID : ");
                if(!isOptionalInfoPassed()){
                    usageTime = 0;
                    paymentAmount = 0;
                }else {
                    System.out.println("Input Usage Time");
                    usageTime = ("end".equalsIgnoreCase(String.valueOf(getIntegerValueOrEnd()))) ? Integer.parseInt(getIntegerValueOrEnd()) : 0;
                    System.out.println("Input Payment Amount");
                    paymentAmount = ("end".equalsIgnoreCase(String.valueOf(getIntegerValueOrEnd()))) ? Integer.parseInt(getIntegerValueOrEnd()) : 0;
                }
                customers.getCustomerList().add(new Customer(name, id, usageTime, paymentAmount));
            }
        }
        return true;
    }

    boolean isOptionalInfoPassed(){
        System.out.println("Do you Want Set Optional Info?");
        String value = "";
        while (true){
            try {
                System.out.print("Wait for Input : ");
                value = br.readLine();
            } catch (IOException e) {
                System.out.println("Please Input Y or N");
            }
            if("y".equalsIgnoreCase(value)){
                return true;
            }
            if("n".equalsIgnoreCase(value)){
                return false;
            }
        }
    }

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            System.out.println("How many Customers to Input ? | type 'end' to cancel");
            isExit = handleChoice(String.valueOf(getIntegerValueOrEnd()));
        }
    }
}
