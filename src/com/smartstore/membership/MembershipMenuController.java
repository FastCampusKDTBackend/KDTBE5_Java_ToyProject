package com.smartstore.membership;

import com.smartstore.menu.Menu;
import com.smartstore.menu.mainmenu.MainMenuFunction;
import com.smartstore.util.CustomList;
import com.smartstore.util.Function;

import java.io.IOException;
import java.util.NoSuchElementException;

public interface MembershipMenuController extends Menu {
    @Override
    default void runMenuSelectionLoop(String[] memberships) {
        String memberTypeName="";
        boolean isExit = false;
        if(memberships.length > 0) {
            while (!isExit) {
                try {
                    System.out.print("Input : ");
                    memberTypeName = br.readLine();
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
        System.out.println();
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

    default void returnToPrevMenu(){
        MainMenuFunction mainMenuFunction = Function.of(1, MainMenuFunction.class);
        mainMenuFunction.run();
    }

    default void run(){
        //get values from enum by string array
        String[] values = getEnumValues();
        displayMenu(values);

        runMenuSelectionLoop(values);
    }

}
