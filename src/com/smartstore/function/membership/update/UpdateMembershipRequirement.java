package com.smartstore.function.membership.update;

import com.smartstore.function.membership.MembershipMenuHandler;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.util.Function;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;


public class UpdateMembershipRequirement implements MembershipMenuHandler {
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

        if(requirement == null ){
            System.out.printf("Membership '%s' Not Defined Yet\n", membershipType.name());
        }else{
            System.out.printf("Current %s Info\n", membershipType.name());
            System.out.printf("Min Usage time : %d\n", requirement.getMinUsageTime());
            System.out.printf("Min Payment Amount: %d\n\n", requirement.getMinPaymentAmount());
            selected = membershipType;
            while (!isExit){
                Printer.printMenu(getMenuListFromEnum(UpdateMembershipRequirementFunction.class));
                //get menu number from user until valid menu number
                isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(UpdateMembershipRequirementFunction.class)));
            }
        }
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == UpdateMembershipRequirementFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with MembershipType
        Function.of(Integer.parseInt(menuNumber), UpdateMembershipRequirementFunction.class).run(selected);
        return true;
    }

    @Override
    public void run(){
        boolean isExit = false;

        while (!isExit){
            Printer.printSelectable(getMenuListFromEnum(MembershipType.class));
            //get menu number from user until valid menu number
            isExit = MembershipMenuHandler.super.handleChoice(Validator.getParameter(getMenuListFromEnum(MembershipType.class)));
        }
    }


}