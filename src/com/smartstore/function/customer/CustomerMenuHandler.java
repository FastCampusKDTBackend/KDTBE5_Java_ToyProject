package com.smartstore.function.customer;

import com.smartstore.function.EnumValueProvider;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.CustomList;

import java.io.IOException;

public interface CustomerMenuHandler extends EnumValueProvider {
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

    default String[] getEnumValues(){
        CustomList<String> keyList = new CustomList<>();
        for(MembershipType enumKey : MembershipType.values()){
            keyList.add(enumKey.name());
        }
        return keyList.toArray(String[].class);
    }

    default void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            printMenu(getEnumValues(MembershipType.class));

            isExit = handleChoice(runMenuSelectionLoop(new String[]{}));
        }
    }

    @Override
    default void printMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("| %s",menus[i]);
        }
        System.out.println(" | or 'end'");
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

}
