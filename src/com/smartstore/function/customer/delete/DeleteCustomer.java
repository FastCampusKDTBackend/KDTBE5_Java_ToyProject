package com.smartstore.function.customer.delete;

import com.smartstore.customer.Customer;
import com.smartstore.customer.Customers;
import com.smartstore.util.Validator;
import com.smartstore.function.customer.CustomerMenuHandler;

public class DeleteCustomer implements CustomerMenuHandler {
    private static DeleteCustomer instance;

    private Customer selected;

    private DeleteCustomer(){

    }

    public static DeleteCustomer getInstance() {
        if(instance == null){
            return new DeleteCustomer();
        }
        return instance;
    }
    @Override
    public void run(){
        String id;
        boolean isExit = false;
        id = Validator.getValueOrEnd("Type User Id to Delete : ", String.class);
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
        if(Validator.isAnswerYes("Did you Really Want to Delete This User? | y or n")){
            Customers.getInstance().getCustomerList().remove(selected);
            System.out.printf("User %s Successfully Deleted\n", id);
        }
    }
}
