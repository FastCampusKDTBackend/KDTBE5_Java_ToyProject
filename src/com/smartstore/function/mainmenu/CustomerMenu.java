package com.smartstore.function.mainmenu;

import com.smartstore.function.Function;
import com.smartstore.function.customer.CustomerFunction;
import com.smartstore.function.membership.MembershipFunction;

public class CustomerMenu implements MainMenuHandler {
    public static CustomerMenu getInstance() {
        if(instance == null){
            instance = new CustomerMenu();
        }
        return instance;
    }

    private static CustomerMenu instance;

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            printMenu(getMenuListFromEnum(CustomerFunction.class));
            //get menu number from user until valid menu number
            isExit = handleChoice(getMenuNumber(getMenuListFromEnum(CustomerFunction.class)));
        }
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == CustomerFunction.BACK.getMenuNumber()) {
            return true;
        }
        Function.of(Integer.parseInt(menuNumber), CustomerFunction.class).run();
        return false;
    }
}
