package com.smartstore.function.sorting;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.util.Function;
import com.smartstore.util.Printer;
import com.smartstore.util.Validator;


public class Sort implements CustomerMenuHandler {

    private static Sort instance;

    private Sort(){

    }

    private Customer selected;

    public static Sort getInstance() {
        if(instance == null){
            return new Sort();
        }
        return instance;
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == SortFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), SortFunction.class).run(selected);
        return false;
    }

    @Override
    public void run(){
        String id="";
        boolean isExit = false;
        id = Validator.getValueOrEnd("Type User Id to Update : ", String.class);
        if("end".equalsIgnoreCase(id)){
            return;
        }
        selected = Customers.getInstance().getCustomerById(id);
        if(selected == null){
            System.out.printf("There is no User %s\n", id);
            return;
        }
        System.out.println("Selected Customer's Info");
        System.out.println(selected.toString());
        System.out.println();
        while (!isExit){
            Printer.printMenu(getMenuListFromEnum(SortFunction.class));
            isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(SortFunction.class)));
        }

    }
}
