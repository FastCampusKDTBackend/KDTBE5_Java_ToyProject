package com.smartstore.function.mainmenu;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.function.sorting.SortFunction;
import com.smartstore.function.sorting.SortHandler;
import com.smartstore.util.Function;
import com.smartstore.util.Handler;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;


public class SummaryMenu implements SortHandler, Handler {

    private static SummaryMenu instance;

    private SummaryMenu(){

    }

    public static SummaryMenu getInstance() {
        if(instance == null){
            return new SummaryMenu();
        }
        return instance;
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == SortFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), SortFunction.class).run();
        return false;
    }

    @Override
    public int getCurrentMenuNumber() {
        return MainMenuFunction.REPORT_MANAGEMENT.getMenuNumber();
    }

    @Override
    public void run(){
        boolean isExit = false;
        while (!isExit){
            Printer.printMenu(getMenuListFromEnum(SortFunction.class));
            isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(SortFunction.class)));
        }

    }

}
