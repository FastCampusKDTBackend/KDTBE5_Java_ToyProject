package com.smartstore.function.membership;

import com.smartstore.function.MenuController;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.CustomList;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface MembershipMenuController extends MenuController {
    @Override
    default void runMenuSelectionLoop(String[] memberships) {
        String memberTypeName="";
        boolean isExit = false;
        if(memberships.length > 0) {
            while (!isExit) {
                try {
                    System.out.print("Input : ");
                    memberTypeName = br.readLine();
                    if("end".equals(memberTypeName)){
                        isExit = true;
                        break;
                    }
                    for (String membershipName : memberships) {
                        if (MembershipType.valueOf(membershipName).isMatchedName(memberTypeName)) {
                            isExit = true;
                            break;
                        }
                    }
                } catch (IOException | NullPointerException e) {
                    System.out.println("Invalid Menu");
                }
            }
            handleChoice(memberTypeName);
        }
    }

    @Override
    default void displayMenu(String[] menus){
        System.out.print("Which One?\n|");
        for(String menu : menus){
            System.out.printf(" %s |",menu);
        }
        System.out.println(" type 'end' to exit");
    }

    default String[] getEnumValues(){
        CustomList<String> keyList = new CustomList<>();
        for(MembershipType membershipType : MembershipType.values()){
            keyList.add(membershipType.name());
        }
        return keyList.toArray(String[].class);
    }

    default MembershipType getMembershipType(String membershipNames){
        String[] values = getEnumValues();
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

    default void run(){
        //get values from enum by string array
        String[] values = getEnumValues();
        displayMenu(values);

        runMenuSelectionLoop(values);
    }

    @Override
    default void handleChoice(String membershipName){
        if(!"end".equalsIgnoreCase(membershipName)){
            MembershipType membershipType = getMembershipType(membershipName);
            MembershipRequirement requirement = Memberships.getInstance().findByType(membershipType);

            //run each function's method
            run(membershipType, requirement);
        }
        //Back to prev Menu
        returnToPrevMenu(1);
    }

    void run(MembershipType membershipType, MembershipRequirement requirement);

}
