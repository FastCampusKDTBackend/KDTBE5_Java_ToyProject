package com.smartstore.function.membership.update;

import com.smartstore.function.Function;
import com.smartstore.function.MenuPrintable;
import com.smartstore.function.SelectSpecificPrintable;
import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;


public class UpdateMembershipRequirement implements MembershipMenuHandler, SelectSpecificPrintable, MenuPrintable {
    private static UpdateMembershipRequirement instance;
    private MembershipType selected;

    private UpdateMembershipRequirement(){

    }

    public static UpdateMembershipRequirement getInstance() {
        if(instance == null){
            return new UpdateMembershipRequirement();
        }
        return instance;
    }

    public void processMembership(MembershipType membershipType, MembershipRequirement requirement){
        boolean isExit = false;
        MenuPrintable menuPrintable = new MenuPrintable() {
            @Override
            public void printMenu(String[] menus) {
                MenuPrintable.super.printMenu(menus);
            }
        };
        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        }else{
            System.out.printf("Current %s Info\n", membershipType.name());
            System.out.printf("Min Usage time : %d\n", requirement.getMinUsageTime());
            System.out.printf("Min Payment Amount: %d\n\n", requirement.getMinPaymentAmount());
            selected = membershipType;
            while (!isExit){
                menuPrintable.printMenu(getEnumValues(UpdateMembershipRequirementFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(getMenuNumber(getEnumValues(UpdateMembershipRequirementFunction.class)));
            }
        }
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == UpdateMembershipRequirementFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), UpdateMembershipRequirementFunction.class).run(selected.ordinal());
        return true;
    }

    @Override
    public void run(){
        boolean isExit = false;
        SelectSpecificPrintable selectSpecificPrintable = new SelectSpecificPrintable() {
            @Override
            public void printMenu(String[] menus) {
                SelectSpecificPrintable.super.printMenu(menus);
            }
        };
        while (!isExit){
            selectSpecificPrintable.printMenu(getEnumValues(MembershipType.class));
            //get menu number from user until valid menu number
            isExit = MembershipMenuHandler.super.handleChoice(getParameter(getEnumValues(MembershipType.class)));
        }
    }


}
