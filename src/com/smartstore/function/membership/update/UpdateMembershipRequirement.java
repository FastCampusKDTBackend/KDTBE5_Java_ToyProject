package com.smartstore.function.membership.update;

import com.smartstore.function.Function;
import com.smartstore.function.MenuPrintable;
import com.smartstore.function.SelectablePrintable;
import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;


public class UpdateMembershipRequirement implements MembershipMenuHandler, SelectablePrintable, MenuPrintable {
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
                menuPrintable.printMenu(getMenuListFromEnum(UpdateMembershipRequirementFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(getMenuNumber(getMenuListFromEnum(UpdateMembershipRequirementFunction.class)));
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

        while (!isExit){
            printSelectable(getMenuListFromEnum(MembershipType.class));
            //get menu number from user until valid menu number
            isExit = MembershipMenuHandler.super.handleChoice(getParameter(getMenuListFromEnum(MembershipType.class)));
        }
    }


}
