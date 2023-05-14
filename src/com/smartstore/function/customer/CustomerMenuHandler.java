package com.smartstore.function.customer;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.function.customer.update.UpdateCustomerFunction;
import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

public interface CustomerMenuHandler extends EnumValueProvider, MenuPrintable, MenuValidator, Handler {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printMenu(getMenuListFromEnum(MembershipType.class));

            isExit = handleChoice(getMenuNumber(new String[]{}));
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
                printMenu(getMenuListFromEnum(UpdateCustomerFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(getMenuNumber(getMenuListFromEnum(UpdateCustomerFunction.class)));
            }
        }
        return true;
    }

    @Override
    default int getCurrentMenuNumber(){
        return MainMenuFunction.CUSTOMER_MANAGEMENT.getMenuNumber();
    };
}
