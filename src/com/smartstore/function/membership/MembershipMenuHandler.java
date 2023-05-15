package com.smartstore.function.membership;

import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.*;

import java.util.NoSuchElementException;

public interface MembershipMenuHandler extends EnumValueProvider, Handler, HandleableParam {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            Printer.printSelectable(getMenuListFromEnum(MembershipType.class, MembershipType.GENERAL.ordinal(), MembershipType.VVIP.ordinal()));

            isExit = handleChoice(Validator.getParameter(getMenuListFromEnum(MembershipType.class)));
        }
    }

    default MembershipType getMembershipType(String membershipNames){
        String[] values = getMenuListFromEnum(MembershipType.class);
        for (String membershipName : values) {
            try{
                if (MembershipType.valueOf(membershipName.toUpperCase()).findByName(membershipNames)) {
                    return MembershipType.valueOf(membershipName.toUpperCase());
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
            //run each function's method
            run(membershipType);
        }
        return true;
    }

    @Override
    default int getCurrentMenuNumber(){
        return MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber();
    }

}
