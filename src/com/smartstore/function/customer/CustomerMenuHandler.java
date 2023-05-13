package com.smartstore.function.customer;

import com.smartstore.function.EnumValueProvider;
import com.smartstore.function.MenuPrintable;
import com.smartstore.function.MenuValidator;
import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.membership.MembershipType;

public interface CustomerMenuHandler extends EnumValueProvider, MenuPrintable, MenuValidator {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printMenu(enumValuesToStringArray(MembershipType.class));

            isExit = handleChoice(getMenuNumber(new String[]{}));
        }
    }
    boolean handleChoice(String membershipName);

    @Override
    default int getCurrentMenuNumber() {
        return MainMenuFunction.CUSTOMER_MANAGEMENT.getMenuNumber();
    }
}
