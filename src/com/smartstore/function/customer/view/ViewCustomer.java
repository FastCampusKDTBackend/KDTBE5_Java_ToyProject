package com.smartstore.function.customer.view;

import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.util.Function;
import com.smartstore.util.Handleable;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;

public class ViewCustomer implements CustomerMenuHandler, Handleable {

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
            Printer.printMenu(getMenuListFromEnum(ViewCustomerFunction.class));
            //get menu number from user until valid menu number
            isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(ViewCustomerFunction.class)));
        }
    }



    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == ViewCustomerFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), ViewCustomerFunction.class).run();
        return true;
    }

}
