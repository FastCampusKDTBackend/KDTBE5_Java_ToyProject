package com.smartstore.function.membership;

import com.smartstore.function.*;
import com.smartstore.function.mainmenu.Screen;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

import java.util.NoSuchElementException;

public interface MembershipMenuHandler extends SelectSpecificPrintable, EnumValueProvider, Handler, ParameterValidator, MenuValidator {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printMenu(enumValuesToStringArray(MembershipType.class));

            isExit = handleChoice(getParameter(enumValuesToStringArray(MembershipType.class)));
        }
    }

    default MembershipType getMembershipType(String membershipNames){
        String[] values = enumValuesToStringArray(MembershipType.class);
        for (String membershipName : values) {
            try{
                if (MembershipType.valueOf(membershipName).findByName(membershipNames)) {
                    return MembershipType.valueOf(membershipName);
                }
            }catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    default boolean handleChoice(String membershipName){
        if(!"end".equalsIgnoreCase(membershipName)){
            //get enum_value using string from MembershipType
            MembershipType membershipType = getMembershipType(membershipName);
            //find requirement using type from enum_map
            MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);
            //run each function's method
            processMembership(membershipType, requirement);
        }
        return true;
    }

    @Override
    default int getCurrentMenuNumber(){
        return Screen.MEMBERSHIP.getMenuNumber();
    }

    void processMembership(MembershipType membershipType, MembershipRequirement requirement);

    @Override
    default String[] enumValuesToStringArray(Class type) {
        return EnumValueProvider.super.enumValuesToStringArray(type);
    }

    @Override
    default void printMenu(String[] menus) {
        SelectSpecificPrintable.super.printMenu(menus);
    }
}
