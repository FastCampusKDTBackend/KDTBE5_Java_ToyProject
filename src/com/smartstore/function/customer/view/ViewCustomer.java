package com.smartstore.function.customer.view;

import com.smartstore.function.*;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.function.membership.update.UpdateMembershipRequirementFunction;

public class ViewCustomer implements CustomerMenuHandler, MenuValidator, Handleable {

    private static ViewCustomer instance;

    private ViewCustomer(){

    }

    public static ViewCustomer getInstance() {
        if(instance == null){
            return new ViewCustomer();
        }
        return instance;
    }
    @Override
    public void run() {
        boolean isExit = false;

        while (!isExit){
            printMenu(getMenuListFromEnum(ViewCustomerFunction.class));
            //get menu number from user until valid menu number
            isExit = handleChoice(getMenuNumber(getMenuListFromEnum(ViewCustomerFunction.class)));
        }
    }



    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == ViewCustomerFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), ViewCustomerFunction.class).run();
        return true;    }
}
