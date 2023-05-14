package com.smartstore.function.customer.add;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.util.CustomList;

import java.util.Arrays;
import java.util.Optional;

public class AddCustomer implements CustomerMenuHandler, MenuPrintable, ValueWithEndValidator,UserDataValidator, AnswerValidator, IdDuplicateChecker {

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
                while (true){
                    id = getUserData(id.getClass(),true, "Input User ID : ");
                    //id duplication Check
                    if(!isIdDuplicated(id)){
                        break;
                    }
                    System.out.printf("User id %s already Exist Try Other id\n", id);
                }

                name = getUserData(name.getClass(),true, "Input User Name : ");
                if(!isAnswerYes("Do you Want Set Optional Info?")){
                    usageTime = 0;
                    paymentAmount = 0;
                }else {
                    String value = getValueOrEnd("Input Usage Time\nWait for Input Type 'end' or '0' will set as 0 : ", Integer.class);
                    usageTime = ("end".equalsIgnoreCase(String.valueOf(value))) ? 0 : Integer.parseInt(value);
                    value = getValueOrEnd("Input Payment Amount\nWait for Input Type 'end' or '0' will set as 0 : ", Integer.class);
                    paymentAmount = ("end".equalsIgnoreCase(String.valueOf(value))) ? 0 : Integer.parseInt(value);
                }
                customers.getCustomerList().add(new Customer(name, id, usageTime, paymentAmount));
            }
        }
        return true;
    }

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            isExit = handleChoice(String.valueOf(getValueOrEnd("How many Customers to Input ? | type 'end' or '0' to cancel ...", Integer.class)));
        }
    }
}
