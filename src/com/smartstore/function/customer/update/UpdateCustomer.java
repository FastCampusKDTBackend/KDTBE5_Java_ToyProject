package com.smartstore.function.customer.update;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.function.*;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.function.customer.view.ViewCustomerFunction;


public class UpdateCustomer implements CustomerMenuHandler, IntegerValidator, ValueWithEndValidator, MenuPrintable {

    private static UpdateCustomer instance;

    private UpdateCustomer(){

    }

    private Customer selected;

    public static UpdateCustomer getInstance() {
        if(instance == null){
            return new UpdateCustomer();
        }
        return instance;
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == UpdateCustomerFunction.BACK.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), UpdateCustomerFunction.class).run(selected);
        return false;
    }

    @Override
    public void run(){
        String id="";
        boolean isExit = false;
        id = getValueOrEnd("Type User Id to Update : ", String.class);
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
            printMenu(getMenuListFromEnum(UpdateCustomerFunction.class));
            isExit = handleChoice(getMenuNumber(getMenuListFromEnum(UpdateCustomerFunction.class)));
        }

    }
}
