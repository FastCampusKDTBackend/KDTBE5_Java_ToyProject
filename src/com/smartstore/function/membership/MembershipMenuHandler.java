package com.smartstore.function.membership;

import com.smartstore.function.*;
import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

import java.util.NoSuchElementException;

public interface MembershipMenuHandler extends SelectablePrintable, EnumValueProvider, Handler, ParameterValidator, MenuValidator {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printSelectable(getMenuListFromEnum(MembershipType.class));

            isExit = handleChoice(getParameter(getMenuListFromEnum(MembershipType.class)));
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
            //find requirement using type from enum_map
            MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);
            //run each function's method
            processMembership(membershipType, requirement);
        }
        return true;
    }

    @Override
    default int getCurrentMenuNumber(){
        return MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber();
    }

    void processMembership(MembershipType membershipType, MembershipRequirement requirement);

}
