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

    @Override
    default boolean handleChoice(String membershipName){
        if(!"end".equalsIgnoreCase(membershipName)){
            //get enum_value using string from MembershipType
    /*        MembershipType membershipType = getMembershipType(membershipName);
            //find requirement using type from enum_map
            MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);

            //run each function's method
            run(membershipType, requirement);
    */    }
        return true;
    }

    @Override
    default int getCurrentMenuNumber() {
        return MainMenuFunction.CUSTOMER_MANAGEMENT.getMenuNumber();
    }
}
