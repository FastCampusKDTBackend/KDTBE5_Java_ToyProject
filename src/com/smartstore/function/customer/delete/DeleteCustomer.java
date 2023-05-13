package com.smartstore.function.customer.delete;

import com.smartstore.function.customer.CustomerMenuHandler;

public class DeleteCustomer implements CustomerMenuHandler {
    private static DeleteCustomer instance;

    private DeleteCustomer(){

    }

    public static DeleteCustomer getInstance() {
        if(instance == null){
            return new DeleteCustomer();
        }
        return instance;
    }


}
