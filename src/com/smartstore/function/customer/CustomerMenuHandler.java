package com.smartstore.function.customer;

import com.smartstore.function.EnumValueProvider;
import com.smartstore.function.Handleable;
import com.smartstore.function.MenuPrintable;
import com.smartstore.function.MenuValidator;
import com.smartstore.membership.MembershipType;

public interface CustomerMenuHandler extends EnumValueProvider, MenuPrintable, MenuValidator, Handleable {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printMenu(getMenuListFromEnum(MembershipType.class));

            isExit = handleChoice(getMenuNumber(new String[]{}));
        }
    }

    boolean handleChoice(String membershipName);

}
