package com.smartstore.function.mainmenu;

import com.smartstore.function.membership.MembershipFunction;
import com.smartstore.util.Function;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;

public class MembershipMenu implements MainMenuHandler {
    private static MembershipMenu instance;
    public static MembershipMenu getInstance() {
        if(instance == null){
            instance = new MembershipMenu();
        }
        return instance;
    }
    private MembershipMenu(){

    }

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            Printer.printMenu(getMenuListFromEnum(MembershipFunction.class));
            //get menu number from user until valid menu number
            isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(MembershipFunction.class)));
        }
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MembershipFunction.BACK.getMenuNumber()) {
            return true;
        }
        Function.of(Integer.parseInt(menuNumber), MembershipFunction.class).run();
        return false;
    }

}

