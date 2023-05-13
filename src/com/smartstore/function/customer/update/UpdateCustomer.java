package com.smartstore.function.customer.update;

import com.smartstore.function.*;
import com.smartstore.function.customer.CustomerMenuHandler;

public class UpdateCustomer implements CustomerMenuHandler, IntegerValidator, MenuPrintable, EnumValueProvider, IntegerWithEndValidator {

    private static UpdateCustomer instance;

    private UpdateCustomer(){

    }

    public static UpdateCustomer getInstance() {
        if(instance == null){
            return new UpdateCustomer();
        }
        return instance;
    }

    @Override
    public boolean handleChoice(String numberOfUser) {
        if(!"end".equalsIgnoreCase(numberOfUser)){
            boolean isExit = false;
            int value=0;
            String[] menus = enumValuesToStringArray(UpdateCustomerFunction.class);
            for(int i = 0 ; i < Integer.parseInt(numberOfUser) ; i++){
                printMenu(menus);
                value = Integer.parseInt(getMenuNumber(menus));
                Function.of(value, UpdateCustomerFunction.class).run();

            }
        }
        return true;
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
