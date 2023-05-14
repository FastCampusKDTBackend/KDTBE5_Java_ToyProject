package com.smartstore.function.customer;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.customer.update.UpdateCustomerFunction;
import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.EnumValueProvider;
import com.smartstore.util.Handler;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;

public interface CustomerMenuHandler extends EnumValueProvider, Handler {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            Printer.printMenu(getMenuListFromEnum(MembershipType.class));

            isExit = handleChoice(Validator.getMenuNumber(new String[]{}));
        }
    }

    @Override
    default boolean handleChoice(String id){
        if(!"end".equalsIgnoreCase(id)){
            //run each function's method
            boolean isExit = false;
            Customer selected;
            selected = Customers.getInstance().getCustomerById(id);
            if(selected == null){
                System.out.printf("There is No User : %s\n", id);
                return false;
            }
            System.out.printf("Current Customer Info\n%s", selected);
            while (!isExit){
                Printer.printMenu(getMenuListFromEnum(UpdateCustomerFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(UpdateCustomerFunction.class)));
            }
        }
        return true;
    }

    @Override
    default int getCurrentMenuNumber(){
        return MainMenuFunction.CUSTOMER_MANAGEMENT.getMenuNumber();
    }
}
