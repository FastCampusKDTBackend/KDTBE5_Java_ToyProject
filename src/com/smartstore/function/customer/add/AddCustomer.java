package com.smartstore.function.customer.add;

import com.smartstore.function.Function;
import com.smartstore.function.customer.CustomerMenuHandler;
import com.smartstore.function.menu.MainMenuFunction;
import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;

import java.io.IOException;

public class AddCustomer implements CustomerMenuHandler {

    private static AddCustomer instance;

    private AddCustomer(){

    }

    public static AddCustomer getInstance() {
        if(instance == null){
            return new AddCustomer();
        }
        return instance;
    }

    public String runMenuSelectionLoop() {
        String value="";
        boolean isExit = false;
        while (!isExit){
            try{
                System.out.print("Input : ");
                value = br.readLine();
                if("end".equals(value) || Integer.parseInt(value)>0){
                    isExit = true;
                    break;
                }
                throw new IllegalArgumentException("Invalid Input");
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                System.out.println("Invalid Input");
            }
        }
        return value;
    }

    public void displayMenu() {
        System.out.println("How many Customers to Input ? | type 'end' to cancel");
    }
    @Override
    public boolean handleChoice(String numberOfUser) {
        if(!"end".equals(numberOfUser)){
            for(int i = 0 ; i < Integer.parseInt(numberOfUser) ; i++){
                Function.of(i, AddCustomerFunction.class);
            }
        }
        return true;
    }

    @Override
    public void run() {
        boolean isExit = false;
        while (!isExit){
            //get values from enum by string array
            displayMenu();

            isExit = handleChoice(runMenuSelectionLoop());
        }
    }
}
