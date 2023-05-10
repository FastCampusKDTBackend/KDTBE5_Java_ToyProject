package com.smartstore.function.membership;

import com.smartstore.function.DisplayMenuByNumber;
import com.smartstore.function.Function;
import com.smartstore.function.FunctionHandler;
import com.smartstore.function.MenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;

import java.io.IOException;
import java.util.Arrays;

public class UpdateMembershipRequirement implements MembershipMenuHandler, MenuHandler {
    private static UpdateMembershipRequirement instance;

    private UpdateMembershipRequirement(){

    }

    public static UpdateMembershipRequirement getInstance() {
        if(instance == null){
            return new UpdateMembershipRequirement();
        }
        return instance;
    }

    public void run(MembershipType membershipType, MembershipRequirement requirement){
        boolean isExit = false;
        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        }else{
            System.out.printf("Current %s Info\n", membershipType.name());
            System.out.printf("Min Usage time : %d\n", requirement.getMinUsageTime());
            System.out.printf("Min Payment Amount: %d\n\n", requirement.getMinPaymentAmount());
            while (!isExit){
                displayMenu(getEnumValues(UpdateMembershipRequirementFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(runMenuSelectionLoop(getEnumValues(UpdateMembershipRequirementFunction.class)));
            }
        }
    }

    @Override
    public String runMenuSelectionLoop(String[] values) {
        int menu = -1;
        while (true){
            try {
                System.out.print("Input : ");
                menu = Integer.parseInt(br.readLine());
                if (menu <= 0 || menu > values.length) {
                    // TODO: 2023-05-08 throw other exception, catch it
                    throw new NumberFormatException("Invalid Menu");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Menu");
            }
        }
        return String.valueOf(menu);
    }

    @Override
    public void displayMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("%d. %s\n",i+1,menus[i]);
        }
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == UpdateMembershipRequirementFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), UpdateMembershipRequirementFunction.class).run();
        return false;
    }

    @Override
    public void run(){
        boolean isExit = false;
        while (!isExit){
            MembershipMenuHandler.super.displayMenu(getEnumValues(MembershipType.class));
            //get menu number from user until valid menu number
            //🤮🤮🤮🤮🤮🤮🤮
            isExit = MembershipMenuHandler.super.handleChoice(MembershipMenuHandler.super.runMenuSelectionLoop(getEnumValues(MembershipType.class)));
        }
    }


}
