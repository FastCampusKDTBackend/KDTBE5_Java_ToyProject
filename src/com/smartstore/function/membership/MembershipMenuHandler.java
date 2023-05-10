package com.smartstore.function.membership;

import com.smartstore.function.FunctionHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.CustomList;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface MembershipMenuHandler extends FunctionHandler {
    @Override
    default String runMenuSelectionLoop(String[] values) {
        String valueName="";
        boolean isExit = false;
        if(values.length > 0) {
            while (!isExit) {
                try {
                    System.out.print("Input : ");
                    valueName = br.readLine();
                    if("end".equals(valueName)){
                        isExit = true;
                        break;
                    }
                    for (String membershipName : values) {
                        if (MembershipType.valueOf(membershipName).isMatchedName(valueName)) {
                            isExit = true;
                            break;
                        }
                    }
                } catch (IOException | NullPointerException e) {
                    System.out.println("Invalid Menu");
                }
            }
        }
        return valueName;
    }


    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            displayMenu(getEnumValues(MembershipType.class));

            isExit = handleChoice(runMenuSelectionLoop(getEnumValues(MembershipType.class)));
        }
    }

    @Override
    default void displayMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("| %s",menus[i]);
        }
        System.out.println(" | or 'end'");
    }

    default MembershipType getMembershipType(String membershipNames){
        String[] values = getEnumValues(MembershipType.class);
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
            run(membershipType, requirement);
        }
        return true;
    }

    void run(MembershipType membershipType, MembershipRequirement requirement);

}
